package IntegriFitGym;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;

public class GymClient extends Application {
    /*
    IntegriFit Gym Client JavaFX start() method
    This is the main menu interface for the client. Since we are using JavaFX, the core logic of the
    client has been defined in the Controller.java class.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmlUserInterfaces/MainMenu.fxml"));
        primaryStage.setTitle("IntegriFit Gym Cient");
        primaryStage.setScene(new Scene(root, 450, 500));
        primaryStage.show();
        Scene scene = primaryStage.getScene();

        Button btnDelete = (Button) scene.lookup("#deleteBtn");
        btnDelete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                try {
                    Parent deleteDataUi = FXMLLoader.load(getClass().getResource("fxmlUserInterfaces/DeleteAccount.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Delete Your Account Data");
                    stage.setScene(new Scene(deleteDataUi));
                    stage.show();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args)
    {
        launch(args);
    }

}
