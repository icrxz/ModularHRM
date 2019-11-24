package sample.forms.controller;

import br.com.ec6.modular.contoller.ProjectDAO;
import br.com.ec6.modular.model.Project;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.Screens;

import java.net.URL;
import java.util.ResourceBundle;

public class CRUDProjectController implements Initializable {

    @FXML
    private TextField txtProjeto;
    @FXML
    private TextField txtCliente;
    @FXML
    private Button btnCriarProjeto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Projeto");

        this.btnCriarProjeto.setOnMouseClicked((MouseEvent e) -> {
            CriarProjeto();
        });

    }

    private void CriarProjeto() {
        Project p = new Project();
        ProjectDAO pDao = new ProjectDAO();

        try {
            String cliente = txtCliente.getText().trim();
            String projeto = txtProjeto.getText().trim();

            if (cliente.length() > 0)
                p.setCustomerName(cliente);
            else
                throw new Exception("Digite o nome do cliente!");
            if (projeto.length() > 0)
                p.setName(projeto);
            else
                throw new Exception("Digite o nome do projeto!");
            p.setProjectCompleted(false);

            pDao.Insere(p);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucesso!");
            alert.setContentText(("Projeto cadastrado com sucesso!"));
            alert.showAndWait();

            Screens.stage.close();
        }
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
}
