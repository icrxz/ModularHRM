package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Screens extends Application {

    private final String PATH_MENU = "forms/view/frMenu.fxml";
    private final String PATH_LOGIN = "forms/view/frLogin.fxml";

    public static Stage stage;
    public static Class classe;
    private String Screen = PATH_LOGIN;

    public String getScreen() {
        return Screen;
    }

    public void setScreen(String screen) {
        Screen = screen;
    }

    public Scene scene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource(getScreen()));

        scene = new Scene(root);

        stage.setScene(scene);
        stage.toFront();
        if(Screen.equals(PATH_MENU) || Screen.equals(PATH_LOGIN))
            stage.show();
        else
            stage.showAndWait();
    }
}
