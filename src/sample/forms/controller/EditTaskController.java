package sample.forms.controller;

import br.com.ec6.modular.contoller.EventDAO;
import br.com.ec6.modular.contoller.TaskDAO;
import br.com.ec6.modular.contoller.TeamMemberDAO;
import br.com.ec6.modular.global.SingletonRowData;
import br.com.ec6.modular.global.Utils;
import br.com.ec6.modular.model.Event;
import br.com.ec6.modular.model.Task;
import br.com.ec6.modular.model.TeamMember;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Screens;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class EditTaskController implements Initializable {
    private Task tRow;
    private Stage janela;

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
    private Button btnEditar;
    @FXML
    private Button btnCancelar;
    @FXML
    private CheckBox checkTask;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tRow = (Task) SingletonRowData.RowData;
        janela = Screens.stage;
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Editar tarefa");

        CarregaComboBox();

        txtNome.setText(tRow.getName());
        txtDesc.setText(tRow.getDescription());
        cbEvento.setValue(tRow.getRelatedEvent());
        cbMembro.setValue(tRow.getAssignedTo());
        dtDataFin.setValue(tRow.getDueDate().toLocalDate());
        checkTask.setSelected(tRow.isTaskCompleted());

        this.btnEditar.setOnMouseClicked((MouseEvent e) -> {
            EditaTarefa();
        });

        this.btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            janela.close();
        });

    }

    private void EditaTarefa(){
        try{
            TaskDAO tDAO = new TaskDAO();

            Event evento = cbEvento.getValue();
            TeamMember membro = cbMembro.getValue();
            String nome = txtNome.getText();
            String desc = txtDesc.getText();
            LocalDateTime data = dtDataFin.getValue().atStartOfDay();

            if(nome.length() > 0)
                tRow.setName(nome);
            else
                throw new Exception("Digite um nome para a tarefa!");
            if(desc.length() > 0)
                tRow.setDescription(desc);
            else
                throw new Exception("Digite a descrição da tarefa!");
            if(!data.equals(null)) {
                if (LocalDateTime.now().isAfter(data))
                    throw new Exception("A data de entrega não pode ser anterior a data atual!");
                tRow.setDueDate(data);
            }
            else
                throw new Exception("Selecione uma data de entrega da tarefa!");
            tRow.setRelatedEvent(evento);
            tRow.setTaskCompleted(checkTask.isSelected());

            if(membro != null)
                tRow.setAssignedTo(membro);
            else
                throw new Exception("Selecione um membro!");

            tDAO.Altera(tRow);
            Utils.MostraAlerta("Sucesso!", "Tarefa alterada com sucesso!", Alert.AlertType.INFORMATION);
            Utils.sendEmailNotification(membro.getMember().getEmail(), tRow, false);
            Utils.MostraAlerta("Sucesso!", "E-mail enviado com sucesso!", Alert.AlertType.INFORMATION);
            janela.close();
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
