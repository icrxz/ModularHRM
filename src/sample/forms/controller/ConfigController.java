package sample.forms.controller;

import br.com.ec6.modular.global.SingletonUserLogged;
import br.com.ec6.modular.model.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Screens;
import br.com.ec6.modular.model.Enum.EnumPermissao;

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
    @FXML
    private Button btnPerfil;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        janela = Screens.stage;
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Configuração");

        if(SingletonUserLogged.UserLogged.getProfile().getPermissionLevel().equals(EnumPermissao.ADMINISTRADOR.getDescricao())){
            btnMembros.setDisable(true);
            btnMembrosTime.setDisable(true);
            btnProjetos.setDisable(true);
            btnTimes.setDisable(true);
            btnPerfil.setDisable(false);
            btnUsuarios.setDisable(false);
        }

        this.btnTimes.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frCRUD.fxml", Team.class);
        });

        this.btnProjetos.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frCRUD.fxml", Project.class);
        });

        this.btnMembrosTime.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frCRUD.fxml", TeamMember.class);
        });

        this.btnMembros.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frCRUD.fxml", Member.class);
        });

        this.btnUsuarios.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frCRUD.fxml", User.class);
        });

        this.btnPerfil.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frCRUD.fxml", Profile.class);
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
