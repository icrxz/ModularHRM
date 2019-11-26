package sample.forms.controller;

import br.com.ec6.modular.contoller.UsersDAO;
import br.com.ec6.modular.global.SingletonUserLogged;
import br.com.ec6.modular.global.Utils;
import br.com.ec6.modular.model.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Screens;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button btnEntrar;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtSenha;
    private Stage janela;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        janela = Screens.stage;
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Login");

        btnEntrar.setOnMouseClicked((MouseEvent e) -> {
            Entrar();
        });

        btnEntrar.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                Entrar();
            }
        });

        txtSenha.setOnKeyPressed((KeyEvent e) -> {
            if (e.getCode() == KeyCode.ENTER) {
                Entrar();
            }
        });

        txtLogin.setOnKeyPressed((KeyEvent e) -> {
            if(e.getCode() == KeyCode.F7)
            {
                try{
                    Screens p = new Screens();
                    p.setScreen("forms/view/frConfigDatabase.fxml");
                    p.start(new Stage());
                }catch (Exception ex){
                    Utils.MostraAlerta("Erro!", ex.getMessage(), Alert.AlertType.ERROR);
                }
            }
        });
    }

    private void Entrar() {
        try {
            UsersDAO uDao = new UsersDAO();
            String login = txtLogin.getText().trim();
            String senha = txtSenha.getText().trim();
            User u = uDao.Login(login, senha);

            if (u != null) {
                SingletonUserLogged sul = SingletonUserLogged.getInstance();
                sul.UserLogged = u;
                Screens p = new Screens();
                p.setScreen("forms/view/frMenu.fxml");
                janela.close();
                p.start(new Stage());
            }else {
                String message = "Usuário não cadastrado, verifique o login e a senha!";
                Utils.MostraAlerta("Erro!", message, Alert.AlertType.INFORMATION);
            }
        } catch (Exception ex) {
            Utils.MostraAlerta("Erro!", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

}
