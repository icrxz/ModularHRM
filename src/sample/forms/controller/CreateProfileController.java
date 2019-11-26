package sample.forms.controller;

import br.com.ec6.modular.contoller.ProfileDAO;
import br.com.ec6.modular.global.Utils;
import br.com.ec6.modular.model.Profile;
import br.com.ec6.modular.model.Enum.EnumPermissao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Screens;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateProfileController implements Initializable {
    @FXML
    private TextField txtNome;
    @FXML
    private ComboBox<EnumPermissao> cbPermission;
    @FXML
    private Button btnCriar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Perfil");

        cbPermission.getItems().setAll(EnumPermissao.values());

        this.btnCriar.setOnMouseClicked((MouseEvent e) -> {
            CriaPerfil();
        });

        this.btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            Screens.stage.close();
        });
    }

    private void CriaPerfil(){
        try {
            Profile p = new Profile();
            ProfileDAO pDAO = new ProfileDAO();

            String name = txtNome.getText().trim();
            String permission = cbPermission.getValue().getDescricao();

            if (name.length() > 0)
                p.setName(name);
            else
                throw new Exception("Digite um nome para o perfil!");
            if(permission.length() > 0)
                p.setPermissionLevel(permission);
            else
                throw new Exception("Escolha a permissão!");

            pDAO.Insere(p);

            Utils.MostraAlerta("Sucesso!", "Perfil criado com sucesso!", Alert.AlertType.INFORMATION);
            Screens.stage.close();
        }catch(Exception ex) {
            Utils.MostraAlerta("Erro!", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
