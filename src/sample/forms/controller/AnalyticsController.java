package sample.forms.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import sample.Screens;

import java.net.URL;
import java.util.ResourceBundle;

public class AnalyticsController implements Initializable {


    @FXML
    private javafx.scene.chart.BarChart<?, ?> BarChart;

    @FXML
    private javafx.scene.chart.AreaChart<?, ?> AreaChart;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Analytics");

    }
}
