package sample.forms.controller;

import br.com.ec6.modular.contoller.ProfileDAO;
import br.com.ec6.modular.contoller.UsersDAO;
import br.com.ec6.modular.model.Profile;
import br.com.ec6.modular.model.User;
import br.com.ec6.modular.global.SingletonUserLogged;
import javafx.fxml.FXML;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button btnEntrar;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtSenha;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        Main.stage.setResizable(false);
        Main.stage.setMaximized(false);
        Main.stage.setTitle("Modular HRM - Login");


        btnEntrar.setOnMouseClicked((MouseEvent e) -> {
            Entrar();
        });

        btnEntrar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                Entrar();
            }
        });
    }

    public void Entrar() {
        try {
            UsersDAO uDao = new UsersDAO();
            String login = txtLogin.getText().trim();
            String senha = txtSenha.getText().trim();
            User u = uDao.Login(login, senha);

            if (u != null) {

                SingletonUserLogged sul = SingletonUserLogged.getInstance();
                sul.UserLogged = u;
                Main p = new Main();
                fechaLogin();
                p.setScreen("forms/view/frMenu.fxml");
                p.start(new Stage());
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erro!");
                alert.setContentText("Usuário não cadastrado, verifique o login e a senha!");
                alert.show();
            }
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    public void fechaLogin() {
        Main.stage.close();
    }

}
