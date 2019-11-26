package sample.forms.controller;

import br.com.ec6.modular.global.SingletonDatabaseConnect;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Screens;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ConfigDatabaseController implements Initializable {
    @FXML
    private TextField txtUsuario;
    @FXML
    private TextField txtSenha;
    @FXML
    private TextField txtServidor;
    @FXML
    private TextField txtxInstancia;
    @FXML
    private Button btnSalvar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Cria Banco");

        btnSalvar.setOnMouseClicked((MouseEvent e) -> {
            String user = txtUsuario.getText().trim();
            String senha = txtSenha.getText().trim();
            String instancia = txtxInstancia.getText().trim();
            String servidor = txtServidor.getText().trim();

            Map<String, String> persistenceMap = new HashMap<String, String>();

            persistenceMap.put("hibernate.connection.url", "jdbc:sqlserver://"+servidor+";instanceName="+instancia+";databaseName=ModularHRM");
            persistenceMap.put("javax.persistence.jdbc.user", user);
            persistenceMap.put("javax.persistence.jdbc.password", senha);

            SingletonDatabaseConnect sdc = SingletonDatabaseConnect.getInstance();
            sdc.persistenceMap = persistenceMap;

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso!");
            alert.setContentText("Dados de conexão salvos!");
            alert.showAndWait();
            Screens.stage.close();
        });
    }
}
