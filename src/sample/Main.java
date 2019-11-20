package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage stage;
    private String Screen = "forms/view/frLogin.fxml";

    public String getScreen() {
        return Screen;
    }

    public void setScreen(String screen) {
        Screen = screen;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource(getScreen()));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
