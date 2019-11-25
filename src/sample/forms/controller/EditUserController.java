package sample.forms.controller;

import br.com.ec6.modular.contoller.ProfileDAO;
import br.com.ec6.modular.contoller.UsersDAO;
import br.com.ec6.modular.global.SingletonRowData;
import br.com.ec6.modular.model.Profile;
import br.com.ec6.modular.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Screens;

import java.net.URL;
import java.util.ResourceBundle;

public class EditUserController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtSenha;
    @FXML
    private TextField txtConfirmarSenha;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<Profile> cbPerfil;
    @FXML
    private Button btnEditarUsuario;
    @FXML
    private Button btnCancelar;

    private Stage janela;
    private User uRow;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        uRow = (User)SingletonRowData.RowData;
        janela = Screens.stage;
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Editar usuario");

        ProfileDAO pDAO = new ProfileDAO();

        cbPerfil.getItems().clear();
        cbPerfil.getItems().addAll(pDAO.SelecionaTodos(Profile.class));

        txtNome.setText(uRow.getName());
        txtUsuario.setText(uRow.getUserName());
        txtSenha.setText(uRow.getPassword());
        txtConfirmarSenha.setText(uRow.getPassword());
        txtEmail.setText(uRow.getEmail());
        cbPerfil.setValue(uRow.getProfile());

        this.btnEditarUsuario.setOnMouseClicked((MouseEvent e) -> {
            AlterarUsuario();
        });

        this.btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            janela.close();
        });

    }

    private void AlterarUsuario(){
        try{
        UsersDAO uDAO = new UsersDAO();

        String usuario = txtUsuario.getText().trim();
        String nome = txtNome.getText().trim();
        String senha = txtSenha.getText().trim();
        String confirmarSenha = txtConfirmarSenha.getText().trim();
        String email = txtEmail.getText().trim();
        Profile p = cbPerfil.getValue();

        if(usuario.length() > 0)
            uRow.setUserName(usuario);
        else
            throw new Exception("Digite um usuário!");
        if(nome.length() > 0)
            uRow.setName(nome);
        else
            throw new Exception("Digite um nome!");
        if(email.length() > 0)
            uRow.setEmail(email);
        else
            throw new Exception("Digite um email!");
        if(senha.length() > 0 && confirmarSenha.length() > 0) {
            if (senha.equals(confirmarSenha)) {
                uRow.setPassword(senha);
            } else {
                throw new Exception("As senhas digitadas não coincidem!");
            }
        }
        else
            throw new Exception("Digite uma senha!");
        if(p != null)
            uRow.setProfile(p);
        else
            throw new Exception("Selecione um perfil!");

        uDAO.Altera(uRow);

        MostraAlerta("Usuário alterado com sucesso!");
        Screens.stage.close();
        }catch (Exception ex){
            MostraAlerta(ex.getMessage());
        }
    }

    private void MostraAlerta(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso!");
        alert.setContentText(message);
        alert.getDialogPane().requestFocus();
        alert.getDialogPane().toFront();
        alert.showAndWait();
    }
}
