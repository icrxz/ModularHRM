package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("forms/view/frLogin.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.setTitle("Modular HRM - Login");
        primaryStage.setScene(scene);
        /*primaryStage.setOnCloseRequest(e -> {
            e.consume();
            primaryStage.setIconified(true);
        });*/
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
