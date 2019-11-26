package sample.forms.controller;

import br.com.ec6.modular.contoller.EventDAO;
import br.com.ec6.modular.contoller.TeamMemberDAO;
import br.com.ec6.modular.global.SingletonRowData;
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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditEventController implements Initializable {

    private Event eRow;

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
    private Button btnEditar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eRow = (Event)SingletonRowData.RowData;
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Editar Evento");

        CarregaComboBox();

        txtEvento.setText(eRow.getName());
        txtDescricao.setText(eRow.getDescription());
        txtHoraFinal.setText(eRow.getDateEnd().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));
        txtHoraIni.setText(eRow.getDateStart().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm")));;
        txtLocal.setText(eRow.getLocation());
        txtTipo.setText(eRow.getType());
        cbTeamMember.setValue(eRow.getResponsibleTeamMember());
        dateFinal.setValue(eRow.getDateEnd().toLocalDate());
        dateInicio.setValue(eRow.getDateStart().toLocalDate());

        this.btnEditar.setOnMouseClicked((MouseEvent e) -> {
            EditaEvento();
        });
        this.btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            Screens.stage.close();
        });
    }

    private void EditaEvento(){
        try{
            EventDAO eDAO = new EventDAO();
            Pattern p = Pattern.compile("^(2[0-3]|[01]?[0-9]):([0-5]?[0-9])$");

            String name = txtEvento.getText().trim();
            String desc = txtDescricao.getText().trim();
            String location = txtLocal.getText().trim();
            LocalDateTime dateS = dateInicio.getValue().atStartOfDay();
            LocalDateTime dateE = dateFinal.getValue().atStartOfDay();
            TeamMember member = cbTeamMember.getValue();
            String type = txtTipo.getText();
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
                eRow.setName(name);
            else
                throw new Exception("Digite um nome para o evento!");

            eRow.setLocation(location);

            if(desc.length() > 0)
                eRow.setDescription(desc);
            else
                throw new Exception("Digite uma descrição para o evento!");

            eRow.setDateStart(dateS);
            eRow.setDateEnd(dateE);

            if(type.length() > 0)
                eRow.setType(type);
            else
                throw new Exception("Digite um tipo para o evento!");
            if(member != null)
                eRow.setResponsibleTeamMember(member);
            else
                throw new Exception("Selecione um membro para o evento!");

            if(!eDAO.validaEvento(eRow))
                throw new Exception("Evento em duplicidade!");

            eDAO.Altera(eRow);
            Utils.MostraAlerta("Sucesso!", "Membro Evento alterado com sucesso!", Alert.AlertType.INFORMATION);
            Utils.sendEmailNotification(eRow.getResponsibleTeamMember().getMember().getEmail(), eRow, false);
            Utils.MostraAlerta("Sucesso!", "E-mail enviado com sucesso!", Alert.AlertType.INFORMATION);
            Screens.stage.close();
        }catch (Exception ex){
            Utils.MostraAlerta("Erro!", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void CarregaComboBox(){
        TeamMemberDAO mDAO = new TeamMemberDAO();

        cbTeamMember.getItems().clear();
        cbTeamMember.getItems().addAll(mDAO.SelecionaTodos(TeamMember.class));
    }
}
