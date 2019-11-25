package sample.forms.controller;

import br.com.ec6.modular.contoller.EventDAO;
import br.com.ec6.modular.contoller.TeamMemberDAO;
import br.com.ec6.modular.global.SingletonUserLogged;
import br.com.ec6.modular.global.Utils;
import br.com.ec6.modular.model.Event;
import br.com.ec6.modular.model.TeamMember;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import sample.Screens;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CreateEventContoller implements Initializable {

    @FXML
    private TextField txtEvento;
    @FXML
    private TextField txtLocal;
    @FXML
    private TextArea txtDescricao;
    @FXML
    private DatePicker dateInicio;
    @FXML
    private DatePicker dateFinal;
    @FXML
    private ComboBox<String> cbTipo;
    @FXML
    private ComboBox<String> cbTeamMember;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Criar Evento");

        CarregaComboBox();

        this.btnSalvar.setOnMouseClicked((MouseEvent e) -> {
            CriaEvento();
        });
        this.btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            Screens.stage.close();
        });

    }

    private void CriaEvento(){
        try {
            Event event = new Event();
            EventDAO eDAO = new EventDAO();

            String name = txtEvento.getText().trim();
            String desc = txtDescricao.getText().trim();
            String location = txtLocal.getText().trim();
            LocalDateTime dateS = dateInicio.getValue().atStartOfDay();
            LocalDateTime dateE = dateFinal.getValue().atStartOfDay();
            String type = cbTipo.getValue();

            event.setName(name);
            event.setLocation(desc);
            event.setDescription(location);
            event.setDateStart(dateS);
            event.setDateEnd(dateE);
            event.setType(type);

            eDAO.Insere(event);
            Utils.sendEmailNotification(event.getResponsibleTeamMember().getMember().getEmail(), event);
            MostraAlerta("Evento criado com sucesso!");
            Screens.stage.close();
        }catch (Exception ex){
            MostraAlerta(ex.getMessage());
        }
    }

    private void CarregaComboBox(){
        TeamMemberDAO mDAO = new TeamMemberDAO();

        cbTeamMember.getItems().clear();
        cbTeamMember.getItems().addAll(mDAO.SelecionaTodos(TeamMember.class));

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
