package sample.forms.controller;

import javafx.fxml.Initializable;
import sample.Screens;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateEventContoller implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Criar Evento");

    }
}
