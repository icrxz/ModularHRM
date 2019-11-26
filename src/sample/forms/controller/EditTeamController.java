package sample.forms.controller;

import br.com.ec6.modular.contoller.ProjectDAO;
import br.com.ec6.modular.contoller.TeamDAO;
import br.com.ec6.modular.global.SingletonRowData;
import br.com.ec6.modular.global.Utils;
import br.com.ec6.modular.model.Project;
import br.com.ec6.modular.model.Team;
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
import java.util.List;
import java.util.ResourceBundle;

public class EditTeamController implements Initializable {
    private Team tRow;
    private Stage janela;

    @FXML
    private Button btnEditTeam;
    @FXML
    private Button btnCancelar;
    @FXML
    private ComboBox<Project> cbProjeto = new ComboBox<>();
    @FXML
    private TextField txtNome;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tRow = (Team) SingletonRowData.RowData;
        janela = Screens.stage;
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Editar time");

        CarregaProjetos();

        txtNome.setText(tRow.getName());
        cbProjeto.setValue(tRow.getProject());

        this.btnEditTeam.setOnMouseClicked((MouseEvent e) ->{
            EditaTime();
        });

        this.btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            janela.close();
        });
    }

    private void EditaTime(){
        try {
            TeamDAO tDao = new TeamDAO();

            String name = txtNome.getText().trim();

            if(name.length() > 0)
                tRow.setName(name);
            else
                throw new Exception("Digite um nome para o time!");

            if (cbProjeto.getItems() != null)
                tRow.setProject(cbProjeto.getValue());
            else
                throw new Exception("Selecione um projeto!");

            tDao.Altera(tRow);
            Utils.MostraAlerta("Sucesso!", "Time alterado com sucesso!", Alert.AlertType.INFORMATION);
            Screens.stage.close();
        }
        catch(Exception ex){
            Utils.MostraAlerta("Erro!", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void CarregaProjetos(){
        try {
            cbProjeto.getItems().clear();

            ProjectDAO pDAO = new ProjectDAO();
            List<Project> projectList = pDAO.ProjetosAtivos();
            if (projectList.size() > 0)
                cbProjeto.getItems().addAll(projectList);
            else{
                Utils.MostraAlerta("Aviso", "Nenhum projeto ativo foi encontrado!", Alert.AlertType.INFORMATION);
            }
        }
        catch (Exception ex){
            Utils.MostraAlerta("Erro!", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
