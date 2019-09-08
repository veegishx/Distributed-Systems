package IntegriFitGym;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GymClient extends Application {
    /*
    IntegriFit Gym Client JavaFX start() method
    This is the main menu interface for the client. Since we are using JavaFX, the core logic of the
    client has been defined in the Controller.java class.
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxmlUserInterfaces/InitializeConnection.fxml"));
        primaryStage.setTitle("IntegriFit Gym Cient");
        primaryStage.setScene(new Scene(root, 450, 250));
        primaryStage.show();
        Scene scene = primaryStage.getScene();


    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
