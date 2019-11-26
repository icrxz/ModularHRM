package sample.forms.controller;

import br.com.ec6.modular.contoller.ProjectDAO;
import br.com.ec6.modular.global.SingletonRowData;
import br.com.ec6.modular.global.Utils;
import br.com.ec6.modular.model.Project;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Screens;

import java.net.URL;
import java.util.ResourceBundle;

public class EditProjectController implements Initializable {
    private Project pRow;
    private Stage janela;

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCliente;
    @FXML
    private CheckBox checkProject;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pRow = (Project) SingletonRowData.RowData;
        janela = Screens.stage;
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Editar projeto");

        txtCliente.setText(pRow.getCustomerName());
        txtNome.setText(pRow.getName());
        checkProject.setSelected(pRow.getProjectCompleted());

        this.btnEditar.setOnMouseClicked((MouseEvent e) -> {
            EditarProjeto();
        });

        this.btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            janela.close();
        });
    }

    private void EditarProjeto(){
        ProjectDAO pDao = new ProjectDAO();

        try {
            String cliente = txtCliente.getText().trim();
            String nome = txtNome.getText().trim();

            if (cliente.length() > 0)
                pRow.setCustomerName(cliente);
            else
                throw new Exception("Digite o nome do cliente!");
            if (nome.length() > 0)
                pRow.setName(nome);
            else
                throw new Exception("Digite o nome do projeto!");
            pRow.setProjectCompleted(false);
            pRow.setProjectCompleted(checkProject.isSelected());

            pDao.Altera(pRow);

            Utils.MostraAlerta("Sucesso!", "Projeto alterado com sucesso!", Alert.AlertType.INFORMATION);

            Screens.stage.close();
        }catch (Exception ex){
            Utils.MostraAlerta("Erro!", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
