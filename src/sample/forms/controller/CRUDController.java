package sample.forms.controller;

import br.com.ec6.modular.contoller.*;
import br.com.ec6.modular.model.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Screens;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class CRUDController implements Initializable {
    private String path;
    private Class classe;

    @FXML
    private TableView tbData;
    @FXML
    private TableColumn clId;
    @FXML
    private Label lblObjectName;
    @FXML
    private Button btnNovo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        classe = Screens.classe;
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Busca");
        ObservableList<Basis> obserList = null;
        tbData.getColumns().clear();

        TableColumn<Basis, Integer> clId = new TableColumn<>("Id");
        clId.setCellValueFactory(f -> new ReadOnlyObjectWrapper<Integer>(f.getValue().getId()));
        clId.setMinWidth(40.0);
        clId.setResizable(false);
        clId.setEditable(false);
        tbData.getColumns().addAll(clId);

        TableColumn<Basis, LocalDateTime> clLastModifiedDate = new TableColumn("Ultima Modificação");
        clLastModifiedDate.setCellValueFactory(f -> new ReadOnlyObjectWrapper<LocalDateTime>(f.getValue().getLastModifiedDate()));
        clLastModifiedDate.setMinWidth(180.0);
        clLastModifiedDate.setResizable(false);
        clLastModifiedDate.setEditable(false);

        this.btnNovo.setOnMouseClicked((MouseEvent e) ->{
            ExecutaTela();
        });

        if(classe == Event.class){
            EventDAO eDAO = new EventDAO();
            lblObjectName.setText("Evento");
            path = "forms/view/frCreateEvent.fxml";
            obserList = FXCollections.observableList(eDAO.SelecionaTodos(Event.class));

            TableColumn<Event, String> clEventName = new TableColumn("Nome");
            clEventName.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getName()));
            clEventName.setMinWidth(100.0);
            clEventName.setResizable(true);
            clEventName.setEditable(true);

            TableColumn<Event, String> clEventDescription = new TableColumn("Descrição");
            clEventDescription.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getDescription()));
            clEventDescription.setMinWidth(100.0);
            clEventDescription.setResizable(true);
            clEventDescription.setEditable(false);

            TableColumn<Event, LocalDateTime> clEventDate = new TableColumn("Data");
            clEventDate.setCellValueFactory(f -> new ReadOnlyObjectWrapper<LocalDateTime>(f.getValue().getDate()));
            clEventDate.setMinWidth(100.0);
            clEventDate.setResizable(true);
            clEventDate.setEditable(false);

            TableColumn<Event, String> clEventLocation = new TableColumn("Local");
            clEventLocation.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getLocation()));
            clEventLocation.setMinWidth(100.0);
            clEventLocation.setResizable(true);
            clEventLocation.setEditable(true);

            TableColumn<Event, String> clEventType = new TableColumn("Tipo");
            clEventType.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getType()));
            clEventType.setMinWidth(100.0);
            clEventType.setResizable(true);
            clEventType.setEditable(false);

            TableColumn<Event, TeamMember> clEventResponsibleMember = new TableColumn("Membro Responsável");
            clEventResponsibleMember.setCellValueFactory(f -> new ReadOnlyObjectWrapper<TeamMember>(f.getValue().getResponsibleTeamMember()));
            clEventResponsibleMember.setMinWidth(100.0);
            clEventResponsibleMember.setResizable(true);
            clEventResponsibleMember.setEditable(false);

            tbData.getColumns().addAll(clEventName, clEventDescription, clEventDate, clEventLocation, clEventType, clEventResponsibleMember);
        }
        else if(classe == Member.class){
            MemberDAO mDAO = new MemberDAO();
            lblObjectName.setText("Membro");
            path = "forms/view/frCRUDMember.fxml";
            obserList = FXCollections.observableList(mDAO.SelecionaTodos(Member.class));

            TableColumn<Member, String> clMemberName = new TableColumn("Nome");
            clMemberName.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getName()));
            clMemberName.setMinWidth(100.0);
            clMemberName.setResizable(true);
            clMemberName.setEditable(false);

            TableColumn<Member, String> clMemberRole = new TableColumn("Cargo");
            clMemberRole.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getRole()));
            clMemberRole.setMinWidth(100.0);
            clMemberRole.setResizable(true);
            clMemberRole.setEditable(true);

            TableColumn<Member, String> clMemberEmail = new TableColumn("E-mail");
            clMemberEmail.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getEmail()));
            clMemberEmail.setMinWidth(150.0);
            clMemberEmail.setResizable(true);
            clMemberEmail.setEditable(true);

            TableColumn<Member, String> clMemberPhone = new TableColumn("Telefone");
            clMemberPhone.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getPhone()));
            clMemberPhone.setMinWidth(100.0);
            clMemberPhone.setResizable(true);
            clMemberPhone.setEditable(true);


            tbData.getColumns().addAll(clMemberName, clMemberRole, clMemberEmail, clMemberPhone);
        }
        else if(classe == Project.class){
            ProjectDAO pDAO = new ProjectDAO();
            lblObjectName.setText("Projeto");
            path = "forms/view/frCRUDProject.fxml";
            obserList = FXCollections.observableList(pDAO.SelecionaTodos(Project.class));

            TableColumn<Project, String> clProjectName = new TableColumn("Nome");
            clProjectName.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getName()));
            clProjectName.setMinWidth(100.0);
            clProjectName.setResizable(true);
            clProjectName.setEditable(true);

            TableColumn<Project, String> clProjectCustomer = new TableColumn("Cliente");
            clProjectCustomer.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getCustomerName()));
            clProjectCustomer.setMinWidth(100.0);
            clProjectCustomer.setResizable(true);
            clProjectCustomer.setEditable(true);

            TableColumn<Project, Boolean> clProjectCompleted = new TableColumn<>("Concluido");
            clProjectCompleted.setCellValueFactory(f -> new ReadOnlyBooleanWrapper(f.getValue().getProjectCompleted()));
            clProjectCompleted.setCellFactory(tc -> new CheckBoxTableCell<>());
            clProjectCompleted.setMinWidth(100.0);
            clProjectCompleted.setResizable(true);
            clProjectCompleted.setEditable(true);

            tbData.getColumns().addAll(clProjectName, clProjectCustomer, clProjectCompleted);
        }
        else if(classe == Task.class){
            TaskDAO tDAO = new TaskDAO();
            lblObjectName.setText("Tarefa");
            path = "forms/view/frCreateTasks.fxml";
            obserList = FXCollections.observableList(tDAO.SelecionaTodos(Task.class));

            TableColumn<Task, String> clTaskName = new TableColumn("Nome");
            clTaskName.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getName()));
            clTaskName.setPrefWidth(100.0);
            clTaskName.setResizable(true);
            clTaskName.setEditable(true);

            TableColumn<Task, String> clTaskDesc = new TableColumn("Descrição");
            clTaskDesc.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getDescription()));
            clTaskDesc.setPrefWidth(100.0);
            clTaskDesc.setResizable(true);
            clTaskDesc.setEditable(true);

            TableColumn<Task, LocalDateTime> clTaskDate = new TableColumn("Data de entrega");
            clTaskDate.setCellValueFactory(f -> new ReadOnlyObjectWrapper<LocalDateTime>(f.getValue().getDueDate()));
            clTaskDate.setPrefWidth(140.0);
            clTaskDate.setResizable(true);
            clTaskDate.setEditable(true);

            TableColumn<Task, Boolean> clTaskCompleted = new TableColumn("Tarefa finalizada");
            clTaskCompleted.setCellValueFactory(f -> new ReadOnlyBooleanWrapper(f.getValue().isTaskCompleted()));
            clTaskCompleted.setCellFactory(tc -> new CheckBoxTableCell<>());
            clTaskCompleted.setPrefWidth(100.0);
            clTaskCompleted.setResizable(true);
            clTaskCompleted.setEditable(true);

            TableColumn<Task, Member> clTaskMember = new TableColumn("Membro");
            clTaskMember.setCellValueFactory(f -> new ReadOnlyObjectWrapper<Member>(f.getValue().getAssignedTo()));
            clTaskMember.setPrefWidth(100.0);
            clTaskMember.setResizable(true);
            clTaskMember.setEditable(true);

            TableColumn<Task, Event> clTaskEvent = new TableColumn("Evento");
            clTaskEvent.setCellValueFactory(f -> new ReadOnlyObjectWrapper<Event>(f.getValue().getRelatedEvent()));
            clTaskEvent.setPrefWidth(100.0);
            clTaskEvent.setResizable(true);
            clTaskEvent.setEditable(true);

            tbData.getColumns().addAll(clTaskName, clTaskDesc, clTaskDate, clTaskMember, clTaskEvent, clTaskCompleted);
        }
        else if(classe == Team.class){
            lblObjectName.setText("Time");
            path = "forms/view/frCRUDTeam.fxml";
            TeamDAO tDAO = new TeamDAO();
            obserList = FXCollections.observableList(tDAO.SelecionaTodos(Team.class));

            TableColumn<Team, String> clTeamName = new TableColumn<>("Nome");
            clTeamName.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getName()));
            clTeamName.setMinWidth(100.0);
            clTeamName.setResizable(true);
            clTeamName.setEditable(true);

            TableColumn<Team, User> clTeamManager = new TableColumn<>("Gerente");
            clTeamManager.setCellValueFactory(f -> new ReadOnlyObjectWrapper<User>(f.getValue().getManager()));
            clTeamManager.setMinWidth(100.0);
            clTeamManager.setResizable(true);
            clTeamManager.setEditable(false);

            TableColumn<Team, Project> clTeamProject = new TableColumn<>("Projeto");
            clTeamProject.setCellValueFactory(f -> new ReadOnlyObjectWrapper<Project>(f.getValue().getProject()));
            clTeamProject.setMinWidth(100.0);
            clTeamProject.setResizable(true);
            clTeamProject.setEditable(false);

            tbData.getColumns().addAll(clTeamName, clTeamManager, clTeamProject);
        }
        else if(classe == User.class){
            UsersDAO uDAO = new UsersDAO();
            lblObjectName.setText("Usuario");
            path = "forms/view/frCreateUser.fxml";
            obserList = FXCollections.observableList(uDAO.SelecionaTodos(User.class));

            TableColumn<User, String> clUserName = new TableColumn<>("Nome");
            clUserName.setMinWidth(100.0);
            clUserName.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getName()));
            clUserName.setResizable(true);
            clUserName.setEditable(true);

            TableColumn<User, String> clUserLogin = new TableColumn<>("Usuário");
            clUserLogin.setMinWidth(100.0);
            clUserLogin.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getUserName()));
            clUserLogin.setResizable(true);
            clUserLogin.setEditable(false);

            TableColumn<User, String> clUserEmail = new TableColumn<>("E-mail");
            clUserEmail.setMinWidth(150.0);
            clUserEmail.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getEmail()));
            clUserEmail.setResizable(true);
            clUserEmail.setEditable(true);

            TableColumn<User, Profile> clUserProfile = new TableColumn<>("Perfil");
            clUserProfile.setMinWidth(100.0);
            clUserProfile.setCellValueFactory(f -> new ReadOnlyObjectWrapper<Profile>(f.getValue().getProfile()));
            clUserProfile.setResizable(true);
            clUserProfile.setEditable(false);

            tbData.getColumns().addAll(clUserLogin, clUserName, clUserEmail, clUserProfile);
        }

        tbData.getColumns().add(clLastModifiedDate);
        if(obserList.size() > 0)
            tbData.setItems(obserList);
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aviso!");
            alert.setContentText("Essa tabela não possui registros!");
            alert.show();
            alert.getDialogPane().requestFocus();
            alert.getDialogPane().toFront();
        }
    }

    public void ExecutaTela() {
        try {
            Screens p = new Screens();
            p.setScreen(path);
            p.start(new Stage());
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro!");
            alert.setContentText(ex.getMessage());
            alert.show();
            alert.getDialogPane().requestFocus();
            alert.getDialogPane().toFront();
        }
    }
}
