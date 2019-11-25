package sample.forms.controller;

import br.com.ec6.modular.global.SingletonUserLogged;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import sample.Screens;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuTasksController implements Initializable {

    @FXML
    private Label lblUsername;

    @FXML
    private Label lblDates;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Agenda");

        lblUsername.setText("Usuário: " + SingletonUserLogged.UserLogged.getName());
    }
}
