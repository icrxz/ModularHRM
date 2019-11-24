package sample.forms.controller;

import br.com.ec6.modular.contoller.EventDAO;
import br.com.ec6.modular.contoller.MemberDAO;
import br.com.ec6.modular.contoller.TaskDAO;
import br.com.ec6.modular.model.Event;
import br.com.ec6.modular.model.Member;
import br.com.ec6.modular.model.Task;
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
    private ComboBox<Member> cbMembro = new ComboBox<>();
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

        });
    }

    private void CriarTarefa(){
        try{
            Task task = new Task();
            TaskDAO tDAO = new TaskDAO();

            Event evento = cbEvento.getValue();
            Member membro = cbMembro.getValue();
            String nome = txtNome.getText();
            String desc = txtDesc.getText();
            LocalDate data = dtDataFin.getValue();

            tDAO.Insere(task);

            MostraAlerta("Tarefa cadastrada com sucesso!");

        }catch (Exception ex){
            MostraAlerta(ex.getMessage());
        }
    }

    private void CarregaComboBox(){
        MemberDAO mDAO = new MemberDAO();
        EventDAO eDAO = new EventDAO();

        cbEvento.getItems().clear();
        cbMembro.getItems().clear();

        cbMembro.getItems().addAll(mDAO.SelecionaTodos(Member.class));
        cbEvento.getItems().addAll(eDAO.EventosAtivos());
    }

    private void MostraAlerta(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Aviso!");
        alert.setContentText(message);
        alert.showAndWait();
        alert.getDialogPane().requestFocus();
        alert.getDialogPane().toFront();
    }
}
