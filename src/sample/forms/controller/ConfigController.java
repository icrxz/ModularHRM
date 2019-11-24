package sample.forms.controller;

import br.com.ec6.modular.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Screens;

import java.net.URL;
import java.util.ResourceBundle;

public class ConfigController implements Initializable {

    private Stage janela;

    @FXML
    private Button btnMembros;
    @FXML
    private Button btnProjetos;
    @FXML
    private Button btnMembrosTime;
    @FXML
    private Button btnTimes;
    @FXML
    private Button btnUsuarios;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        janela = Screens.stage;
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Configuração");

        this.btnTimes.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frCRUD.fxml", Team.class);
        });

        this.btnProjetos.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frCRUD.fxml", Project.class);
        });

        this.btnMembrosTime.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frCRUDTeamMember.fxml", TeamMember.class);
        });

        this.btnMembros.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frCRUD.fxml", Member.class);
        });

        this.btnUsuarios.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frCRUD.fxml", User.class);
        });
    }

    public void ExecutaTela(String screen, Class c) {
        try {
            Screens p = new Screens();
            p.classe = c;
            p.setScreen(screen);
            p.start(new Stage());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }
}
