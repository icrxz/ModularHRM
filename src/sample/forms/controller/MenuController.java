package sample.forms.controller;

import br.com.ec6.modular.contoller.TaskDAO;
import br.com.ec6.modular.contoller.TeamDAO;
import br.com.ec6.modular.global.SingletonUserLogged;
import br.com.ec6.modular.model.Event;
import br.com.ec6.modular.model.Task;
import br.com.ec6.modular.model.Team;
import br.com.ec6.modular.model.User;
import br.com.ec6.modular.model.Enum.EnumPermissao;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.Screens;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button btnAgenda;
    @FXML
    private Button btnAnalytics;
    @FXML
    private Button btnTarefas;
    @FXML
    private Button btnConfig;
    @FXML
    private Label lblUser;
    @FXML
    private Label lblCargo;
    @FXML
    private Label lblResumo1;
    @FXML
    private Label lblResumo2;
    @FXML
    private Text lblDesc;

    private Stage janela;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        janela = Screens.stage;
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Menu Principal");
        User uLog = SingletonUserLogged.UserLogged;

        lblUser.setText("Usuário: " + uLog.getName());
        lblCargo.setText("Perfil: " + uLog.getProfile().getName());
        AtualizaLabel();

        if(uLog.getProfile().getPermissionLevel().equals(EnumPermissao.ADMINISTRADOR.getDescricao())){
            btnAgenda.setDisable(true);
            btnAnalytics.setDisable(true);
            btnTarefas.setDisable(true);
        }

       this.btnAgenda.setOnMouseEntered((MouseEvent e) -> {
            this.lblDesc.setText("A partir desta tela é possível verificar as tarefas agendadas para os usuários, "
                    + "além de permitir o gerente adicionar novos eventos para a sua equipe!");
        });
        this.btnAgenda.setOnMouseExited((MouseEvent e) -> {
            this.lblDesc.setText("");
        });
        this.btnAgenda.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frCRUD.fxml", Event.class);
        });

        this.btnAnalytics.setOnMouseEntered((MouseEvent e) -> {
            this.lblDesc.setText("A partir desta tela é possível verficar gráficos de eventos e análises profundas dos projetos e equipes!");
        });
        this.btnAnalytics.setOnMouseExited((MouseEvent e) -> {
            this.lblDesc.setText("");
        });
        this.btnAnalytics.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frAnalytics.fxml", null);
        });

        this.btnConfig.setOnMouseEntered((MouseEvent e) -> {
            this.lblDesc.setText("Configurações gerais do sistema, cadastro de times, projetos, membros, usuários e perfis!");
        });
        this.btnConfig.setOnMouseExited((MouseEvent e) -> {
            this.lblDesc.setText("");
        });
        this.btnConfig.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frConfig.fxml", null);
        });

        this.btnTarefas.setOnMouseEntered((MouseEvent e) -> {
            this.lblDesc.setText("Cadastro e visualização de tarefas relacionadas os não com eventos cadastrados para os membros de uma equipe!");
        });
        this.btnTarefas.setOnMouseExited((MouseEvent e) -> {
            this.lblDesc.setText("");
        });
        this.btnTarefas.setOnMouseClicked((MouseEvent e) -> {
            ExecutaTela("forms/view/frCRUD.fxml", Task.class);
        });
    }

    public void ExecutaTela(String screen, Class c) {
        try {
            Screens p = new Screens();
            p.classe = c;
            p.setScreen(screen);
            janela.hide();
            p.start(new Stage());
            janela.show();
            AtualizaLabel();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    private void AtualizaLabel(){
        TaskDAO tDAO = new TaskDAO();
        TeamDAO teDAO = new TeamDAO();
        List<Task> tarefasAbertas = tDAO.SelecionaTodos(Task.class);

        lblResumo1.setText("Tarefas Abertas: " + tarefasAbertas.stream().filter(x -> x.isTaskCompleted() == false).count());
        lblResumo2.setText("Times Criados: " + teDAO.SelecionaTodos(Team.class).size());
    }
}
