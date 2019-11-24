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

        /*Parent root = FXMLLoader.load(getClass().getResource("forms/view/frMenu.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setResizable(false);
        primaryStage.setMaximized(false);
        primaryStage.setTitle("Modular HRM - Menu Principal");
        primaryStage.setScene(scene);
        /*primaryStage.setOnCloseRequest(e -> {
            e.consume();
            primaryStage.setIconified(true);
        });
        stage = primaryStage;
        primaryStage.show();*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}