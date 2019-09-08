//package IntegriFitGym;
//
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//
//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("fxmlUserInterfaces/Create.fxml"));
//        primaryStage.setTitle("IntegriFit Gym Cient");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
//        Scene scene = primaryStage.getScene();
//
//
//        TextField firstNameTextBox = (TextField) scene.lookup("#firstNameTextBox");
//        String firstName = firstNameTextBox.getText();
//
//        TextField lastNameTextBox = (TextField) scene.lookup("#lastNameTextBox");
//        String lastName = lastNameTextBox.getText();
//
//        TextField phoneNumberTextBox = (TextField) scene.lookup("#phoneNumberTextBox");
//        String phoneNumber = phoneNumberTextBox.getText();
//
//        TextField txt = (TextField) scene.lookup("#firstNameTextBox");
//       // String firstName = txt.getText();
//
//        Button btn = (Button) scene.lookup("#mainMenuBtn");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent e) {
//                System.out.println(txt.getText());
//            }
//        });
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
