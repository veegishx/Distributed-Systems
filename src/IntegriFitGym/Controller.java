package IntegriFitGym;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller {
    public Label fetchFirstName;
    public Label fetchLastName;
    public Label fetchNationalId;
    public Label fetchPhoneNumber;
    public Label fetchMembershipDuration;
    public Label fetchMedicalTreament;
    public Label fetchMedications;
    public Label fetchDoctorName;
    public Label fetchDoctorNumber;
    public RadioButton updateRadio1;
    public ToggleGroup updateMedicalCondition;
    public RadioButton updateRadio2;
    public TextField updateMedications;
    public TextField updateDoctorName;
    public TextField updateDoctorNumber;
    public Button updateInfoBtn;
    public TextField updateResidentialAddress;
    public Slider updateMembershipDuration;
    public TextField deleteNationalId;
    @FXML
    private TextField firstNameTextBox;
    @FXML
    private TextField lastNameTextBox;
    @FXML
    private TextField phoneNumberTextBox;
    @FXML
    private Slider membershipDurationSlider;
    @FXML
    private TextField medicationsTextBox;
    @FXML
    private RadioButton radio1;
    @FXML
    private RadioButton radio2;
    @FXML
    private TextField doctorNameTextBox;
    @FXML
    private TextField doctorContactNumTextBox;
    @FXML
    private TextField nationalIdTextBox;
    @FXML
    private TextField memberAddressTextBox;
    @FXML
    private TextField updateFirstNameTextBox;
    @FXML
    private TextField updateNationalId;
    @FXML
    private TextField updateLastNameTextBox;
    @FXML
    private TextField updatePhoneNumberTextBox;
    

    public static Scanner in = new Scanner(System.in);
    public final static String hostname = "localhost";
    public final static int portNumber = 59090;
    private Socket connectionSocket;
    private static GymData clientData;
    public static ObjectInputStream objInStream;
    public static ObjectOutput objOutStream;
    public static ArrayList<GymData> gymDataArrayList = new ArrayList<GymData>();

    public void connect() {
        try {
            this.connectionSocket = new Socket(hostname, portNumber);

            InputStream responseFromServer = this.connectionSocket.getInputStream();
            objInStream = new ObjectInputStream(responseFromServer);

            OutputStream requestToServer = this.connectionSocket.getOutputStream();
            objOutStream = new ObjectOutputStream(requestToServer);

        } catch (UnknownHostException uhe) {
            uhe.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
   }

    public void disconnect(){
        try{
            this.objInStream.close();
            this.objOutStream.close();
            this.connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteID(){
        try{
            System.out.println("Enter User ID to delete:");
            String nid = in.nextLine();

            // send action message
            objOutStream.writeUTF("deleteID");
            objOutStream.flush();

            objOutStream.writeUTF(nid);
            objOutStream.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void deleteAll(){
        try{
            // send action message
            objOutStream.writeUTF("deleteAll");
            objOutStream.flush();

            System.out.println(objInStream.readUTF());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadRegistrationUi(ActionEvent actionEvent) {
        try {
            Parent registerUi = FXMLLoader.load(getClass().getResource("fxmlUserInterfaces/Create.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Membership Registration");
            stage.setScene(new Scene(registerUi));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadFetchDataUi(ActionEvent actionEvent) {
        try {
            Parent registerUi = FXMLLoader.load(getClass().getResource("fxmlUserInterfaces/FetchData.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Fetch User Data");
            stage.setScene(new Scene(registerUi));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadUpdateUi(ActionEvent actionEvent) {
        try {
            Parent registerUi = FXMLLoader.load(getClass().getResource("fxmlUserInterfaces/UpdateAccount.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Update User Data");
            stage.setScene(new Scene(registerUi));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void loadDeleteUi(ActionEvent actionEvent) {
        try {
            Parent registerUi = FXMLLoader.load(getClass().getResource("fxmlUserInterfaces/DeleteAccount.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(registerUi));
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void handleRegistration(ActionEvent actionEvent) {
        try {

            connect();
            Parent registerUi = FXMLLoader.load(getClass().getResource("fxmlUserInterfaces/Create.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Register");
            stage.setScene(new Scene(registerUi));
            Scene scene = stage.getScene();

            String nationalId = nationalIdTextBox.getText();
            String firstName = firstNameTextBox.getText();
            String lastName = lastNameTextBox.getText();
            String phoneNumber = phoneNumberTextBox.getText();
            int membershipDuration = (int) membershipDurationSlider.getValue();
            String memberAddress = memberAddressTextBox.getText();

            final ToggleGroup medicalConditionGroup = new ToggleGroup();
            radio1.setToggleGroup(medicalConditionGroup);
            radio2.setToggleGroup(medicalConditionGroup);
            RadioButton selectedRadioButton = (RadioButton) medicalConditionGroup.getSelectedToggle();
            String medicalConditionGroupValue = selectedRadioButton.getText();

            String medications = medicationsTextBox.getText();
            String doctorName = doctorNameTextBox.getText();
            String doctorContactNum = doctorContactNumTextBox.getText();

            clientData = new GymData(nationalId, firstName, lastName, phoneNumber, membershipDuration, memberAddress, medicalConditionGroupValue, medications, doctorName, doctorContactNum);
            // send action message
            objOutStream.writeUTF("create");
            objOutStream.flush();
            // send object
            objOutStream.writeObject(clientData);
            objOutStream.flush();
            clientData = null;

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gym Client Status Alert");
            alert.setHeaderText("Account Successfully Created");
            alert.setContentText("Press OK to continue");
            Optional<ButtonType> result = alert.showAndWait();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    public void handleFetchData(ActionEvent actionEvent) {
        try{
            connect();
            // send action message
            objOutStream.writeUTF("readAll");
            objOutStream.flush();
            objOutStream = null;
            gymDataArrayList = (ArrayList) objInStream.readObject();
            System.out.println(gymDataArrayList.get(gymDataArrayList.size() - 1).getFirstName());
            gymDataArrayList.get(gymDataArrayList.size() - 1);
            fetchFirstName.setText(gymDataArrayList.get(gymDataArrayList.size() - 1).getFirstName());
            fetchLastName.setText(gymDataArrayList.get(gymDataArrayList.size() - 1).getLastName());
            fetchNationalId.setText(gymDataArrayList.get(gymDataArrayList.size() - 1).getNationalId());
            fetchPhoneNumber.setText(gymDataArrayList.get(gymDataArrayList.size() - 1).getPhoneNumber());
            fetchMembershipDuration.setText(gymDataArrayList.get(gymDataArrayList.size() - 1).getMembershipDuration() + " Months");
            fetchMedications.setText(gymDataArrayList.get(gymDataArrayList.size() - 1).getMedicalCondition());
            fetchMedicalTreament.setText(gymDataArrayList.get(gymDataArrayList.size() - 1).getMedicationName());
            fetchDoctorName.setText(gymDataArrayList.get(gymDataArrayList.size() - 1).getDoctorName());
            fetchDoctorNumber.setText(gymDataArrayList.get(gymDataArrayList.size() - 1).getDoctorNumber());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gym Client Status Alert");
            alert.setHeaderText("Data Successfully Fetched");
            alert.setContentText("Press OK to continue");
            Optional<ButtonType> result = alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void handleUpdateData(ActionEvent actionEvent) {
        try{
            connect();
            String nid = updateNationalId.getText();
            String firstName = updateFirstNameTextBox.getText();
            String lastName = updateLastNameTextBox.getText();
            String phoneNumber = updatePhoneNumberTextBox.getText();
            String address = updateResidentialAddress.getText();
            int duration = (int) updateMembershipDuration.getValue();

            final ToggleGroup medicalConditionGroup = new ToggleGroup();
            updateRadio1.setToggleGroup(medicalConditionGroup);
            updateRadio2.setToggleGroup(medicalConditionGroup);
            RadioButton selectedRadioButton = (RadioButton) medicalConditionGroup.getSelectedToggle();
            String medicalConditionGroupValue = selectedRadioButton.getText();

            String medication = updateMedications.getText();
            String doctor = updateDoctorName.getText();
            String doctorNum = updateDoctorNumber.getText();

            GymData updatedGymData = new GymData(nid, firstName, lastName, phoneNumber, duration, address, medicalConditionGroupValue, medication, doctor, doctorNum);

            // send action message
            objOutStream.writeUTF("update");
            objOutStream.flush();

            objOutStream.writeUTF(nid);
            objOutStream.flush();

            objOutStream.writeObject(updatedGymData);
            objOutStream.flush();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gym Client Status Alert");
            alert.setHeaderText("Account Successfully Updated");
            alert.setContentText("Press OK to continue");
            Optional<ButtonType> result = alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleDeleteData(ActionEvent actionEvent) {
        connect();
        try{
            String nid = deleteNationalId.getText();

            // send action message
            objOutStream.writeUTF("deleteID");
            objOutStream.flush();

            objOutStream.writeUTF(nid);
            objOutStream.flush();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Gym Client Status Alert");
            alert.setHeaderText("Account Successfully Deleted");
            alert.setContentText("Press OK to continue");
            Optional<ButtonType> result = alert.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
