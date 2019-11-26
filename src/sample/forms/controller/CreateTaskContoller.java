package sample.forms.controller;

import br.com.ec6.modular.contoller.EventDAO;
import br.com.ec6.modular.contoller.MemberDAO;
import br.com.ec6.modular.contoller.TaskDAO;
import br.com.ec6.modular.contoller.TeamMemberDAO;
import br.com.ec6.modular.global.Utils;
import br.com.ec6.modular.model.Event;
import br.com.ec6.modular.model.Member;
import br.com.ec6.modular.model.Task;
import br.com.ec6.modular.model.TeamMember;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import net.bytebuddy.asm.Advice;
import sample.Screens;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CreateTaskContoller implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private ComboBox<Event> cbEvento = new ComboBox<>();
    @FXML
    private ComboBox<TeamMember> cbMembro = new ComboBox<>();
    @FXML
    private DatePicker dtDataFin;
    @FXML
    private TextArea txtDesc;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Tarefas");

        CarregaComboBox();

        this.btnSalvar.setOnMouseClicked((MouseEvent e) -> {
            CriarTarefa();
        });

        this.btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            Screens.stage.close();
        });
    }

    private void CriarTarefa(){
        try{
            Task task = new Task();
            TaskDAO tDAO = new TaskDAO();

            Event evento = cbEvento.getValue();
            TeamMember membro = cbMembro.getValue();
            String nome = txtNome.getText().trim();
            String desc = txtDesc.getText().trim();
            LocalDateTime data = dtDataFin.getValue().atStartOfDay();

            if(nome.length() > 0)
                task.setName(nome);
            else
                throw new Exception("Digite um nome para a tarefa!");
            if(desc.length() > 0)
                task.setDescription(desc);
            else
                throw new Exception("Digite a descrição da tarefa!");
            if(!data.equals(null)) {
                if (LocalDateTime.now().isAfter(data))
                    throw new Exception("A data de entrega não pode ser anterior a data atual!");
                task.setDueDate(data);
            }
            else
                throw new Exception("Selecione uma data de entrega da tarefa!");

            task.setRelatedEvent(evento);
            task.setTaskCompleted(false);

            if(membro != null)
                task.setAssignedTo(membro);
            else
                throw new Exception("Selecione um membro!");

            tDAO.Insere(task);
            Utils.MostraAlerta("Sucesso!", "Tarefa cadastrada com sucesso!", Alert.AlertType.INFORMATION);
            Utils.sendEmailNotification(membro.getMember().getEmail(), task, true);
            Utils.MostraAlerta("Sucesso!", "E-mail enviado com sucesso!", Alert.AlertType.INFORMATION);
            Screens.stage.close();
        }catch (Exception ex){
            Utils.MostraAlerta("Erro!", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void CarregaComboBox(){
        TeamMemberDAO mDAO = new TeamMemberDAO();
        EventDAO eDAO = new EventDAO();

        cbEvento.getItems().clear();
        cbMembro.getItems().clear();

        cbMembro.getItems().addAll(mDAO.SelecionaTodos(TeamMember.class));
        cbEvento.getItems().addAll(eDAO.EventosAtivos());
    }
}
