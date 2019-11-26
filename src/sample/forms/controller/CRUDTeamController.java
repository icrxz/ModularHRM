package sample.forms.controller;

import br.com.ec6.modular.contoller.ProjectDAO;
import br.com.ec6.modular.contoller.TeamDAO;
import br.com.ec6.modular.global.SingletonUserLogged;
import br.com.ec6.modular.global.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Screens;
import br.com.ec6.modular.model.Team;
import br.com.ec6.modular.model.Project;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CRUDTeamController implements Initializable {

    @FXML
    private Button btnCriarTime;
    @FXML
    private ComboBox<Project> cbProjeto = new ComboBox<>();
    @FXML
    private TextField txtTime;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Time");

        txtTime.requestFocus();

        CarregaProjetos();

        btnCriarTime.setOnMouseClicked((MouseEvent e) -> {
            CriaTime();
        });
    }

    private void CarregaProjetos(){
        try {
            cbProjeto.getItems().clear();

            ProjectDAO pDAO = new ProjectDAO();
            List<Project> projectList = pDAO.ProjetosAtivos();
            if (projectList.size() > 0)
                cbProjeto.getItems().addAll(projectList);
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Aviso!");
                alert.setContentText("Nenhum projeto ativo foi encontrado!");
                alert.show();
            }

        }
        catch (Exception ex){
            Utils.MostraAlerta("Erro!", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void CriaTime(){
        try {
            Team t = new Team();
            TeamDAO tDao = new TeamDAO();

            String name = txtTime.getText().trim();

            if(name.length() > 0)
                t.setName(name);
            else
                throw new Exception("Digite um nome para o time!");

            if (cbProjeto.getItems() != null)
                t.setProject(cbProjeto.getValue());
            else
                throw new Exception("Selecione um projeto!");

            t.setManager(SingletonUserLogged.UserLogged);

            tDao.Insere(t);
            Utils.MostraAlerta("Sucesso!", "Time cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            Screens.stage.close();
        }
        catch(Exception ex){
            Utils.MostraAlerta("Erro!", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
