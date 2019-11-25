package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Screens s = new Screens();
        s.setScreen("forms/view/frLogin.fxml");
        s.start(new Stage());
    }

    public static void main(String[] args) {
        launch(args);
    }
}