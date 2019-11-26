package sample.forms.controller;

import br.com.ec6.modular.contoller.MemberDAO;
import br.com.ec6.modular.contoller.ProfileDAO;
import br.com.ec6.modular.contoller.UsersDAO;
import br.com.ec6.modular.global.Utils;
import br.com.ec6.modular.model.Member;
import br.com.ec6.modular.model.Profile;
import br.com.ec6.modular.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.hibernate.exception.ConstraintViolationException;
import sample.Screens;

import java.awt.color.ProfileDataException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {

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
    private ComboBox<Profile> cbPerfil = new ComboBox<>();
    @FXML
    private Button btnCriarUsuario;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Usuário");

        ProfileDAO pDAO = new ProfileDAO();

        cbPerfil.getItems().clear();
        cbPerfil.getItems().addAll(pDAO.SelecionaTodos(Profile.class));

        this.btnCriarUsuario.setOnMouseClicked((MouseEvent e) -> {
            AdicionaUsuario();
        });

        this.btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            Screens.stage.close();
        });
    }

    private void AdicionaUsuario(){
        try{
            User user = new User();
            UsersDAO uDAO = new UsersDAO();

            String usuario = txtUsuario.getText().trim();
            String nome = txtNome.getText().trim();
            String senha = txtSenha.getText().trim();
            String confirmarSenha = txtConfirmarSenha.getText().trim();
            String email = txtEmail.getText().trim();
            Profile p = cbPerfil.getValue();

            if(usuario.length() > 0)
                user.setUserName(usuario);
            else
                throw new Exception("Digite um usuário!");
            if(nome.length() > 0)
                user.setName(nome);
            else
                throw new Exception("Digite um nome!");
            if(email.length() > 0)
                user.setEmail(email);
            else
                throw new Exception("Digite um email!");
            if(senha.length() > 0 && confirmarSenha.length() > 0) {
                if (senha.equals(confirmarSenha)) {
                    user.setPassword(senha);
                } else {
                    throw new Exception("As senhas digitadas não coincidem!");
                }
            }
            else
                throw new Exception("Digite uma senha!");
            if(p != null)
                user.setProfile(p);
            else
                throw new Exception("Selecione um perfil!");

            uDAO.Insere(user);
            Utils.MostraAlerta("Sucesso!", "Usuário cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            Screens.stage.close();
        }catch (ConstraintViolationException ex){
            Utils.MostraAlerta("Aviso!", "Usuário já cadastrado!", Alert.AlertType.WARNING);
        }
        catch(Exception ex){
            Utils.MostraAlerta("Erro!", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
