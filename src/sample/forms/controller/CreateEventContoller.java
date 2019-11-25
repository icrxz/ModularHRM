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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import sample.Screens;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateEventContoller implements Initializable {

    @FXML
    private TextField txtEvento;
    @FXML
    private TextField txtLocal;
    @FXML
    private TextField txtHoraIni;
    @FXML
    private TextField txtHoraFinal;
    @FXML
    private TextArea txtDescricao;
    @FXML
    private DatePicker dateInicio;
    @FXML
    private DatePicker dateFinal;
    @FXML
    private TextField txtTipo;
    @FXML
    private ComboBox<TeamMember> cbTeamMember;
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
            Pattern p = Pattern.compile("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$");

            String name = txtEvento.getText().trim();
            String desc = txtDescricao.getText().trim();
            String location = txtLocal.getText().trim();
            LocalDateTime dateS = dateInicio.getValue().atStartOfDay();
            LocalDateTime dateE = dateFinal.getValue().atStartOfDay();
            String type = txtTipo.getText();
            TeamMember member = cbTeamMember.getValue();
            String timeIni;
            String timeFim;
            Matcher m = p.matcher(txtHoraIni.getText().trim());
            if(m.matches())
                timeIni = txtHoraIni.getText().trim();
            else
                throw new Exception("Digite a hora inicial no formato correto!");
            m = p.matcher(txtHoraFinal.getText().trim());
            if(m.matches())
                timeFim = txtHoraFinal.getText().trim();
            else
                throw new Exception("Digite a hora final no formato correto!");

            dateS = dateS.with(LocalTime.parse(timeIni));
            dateE = dateE.with(LocalTime.parse(timeFim));

            if(dateS.isAfter(dateE))
                throw new Exception("Data de início não pode ser posterior a data final");

            if(name.length() > 0)
                event.setName(name);
            event.setLocation(location);
            if(desc.length() > 0)
                event.setDescription(desc);
            event.setDateStart(dateS);
            event.setDateEnd(dateE);
            event.setType(type);
            if(member != null)
                event.setResponsibleTeamMember(member);
            else
                throw new Exception("Selecione um membro para o evento!");

            if(!eDAO.validaEvento(event))
                throw new Exception("Evento em duplicidade!");

            eDAO.Insere(event);
            MostraAlerta("Evento criado com sucesso!");
            Utils.sendEmailNotification(event.getResponsibleTeamMember().getMember().getEmail(), event);
            MostraAlerta("E-mail enviado com sucesso!");
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
