package sample.forms.controller;

import br.com.ec6.modular.contoller.*;
import br.com.ec6.modular.global.SingletonRowData;
import br.com.ec6.modular.global.SingletonUserLogged;
import br.com.ec6.modular.global.Utils;
import br.com.ec6.modular.model.*;
import br.com.ec6.modular.model.Enum.EnumPermissao;
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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class CRUDController implements Initializable {
    private String path;
    private String pathEdit = null;
    private Class classe;

    @FXML
    private TableView tbData;
    @FXML
    private Label lblObjectName;
    @FXML
    private Button btnNovo;

    ObservableList<Basis> obserList = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        classe = Screens.classe;
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Busca");
        obserList = null;
        tbData.getColumns().clear();

        if(SingletonUserLogged.UserLogged.getProfile().getPermissionLevel().equals(EnumPermissao.GERENTE.getDescricao())){
            btnNovo.setDisable(false);
        }
        else if(SingletonUserLogged.UserLogged.getProfile().getPermissionLevel().equals(EnumPermissao.ADMINISTRADOR.getDescricao())){
            if(classe == Profile.class || classe == User.class)
                btnNovo.setDisable(false);
        }

        TableColumn<Basis, Integer> clId = new TableColumn<>("Id");
        clId.setCellValueFactory(f -> new ReadOnlyObjectWrapper<Integer>(f.getValue().getId()));
        clId.setPrefWidth(40.0);
        clId.setResizable(false);
        clId.setEditable(false);
        tbData.getColumns().addAll(clId);

        TableColumn<Basis, String> clLastModifiedDate = new TableColumn("Ultima Modificação");
        clLastModifiedDate.setCellValueFactory(f -> new ReadOnlyObjectWrapper<String>(f.getValue().getLastModifiedDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))));
        clLastModifiedDate.setMinWidth(180.0);
        clLastModifiedDate.setResizable(false);
        clLastModifiedDate.setEditable(false);

        this.btnNovo.setOnMouseClicked((MouseEvent e) ->{
            ExecutaTela();
        });

        if(classe == Event.class){
            GeraEvento();
        }
        else if(classe == Member.class){
            GeraMembro();
        }
        else if(classe == Project.class){
            GeraProjeto();
        }
        else if(classe == Task.class){
            GeraTarefa();
        }
        else if(classe == Team.class){
            GeraTime();
        }
        else if(classe == TeamMember.class){
            GeraTimeMembro();
        }
        else if(classe == Profile.class){
            GeraPerfil();
        }
        else if(classe == User.class){
            GeraUsuario();
        }
        tbData.getColumns().add(clLastModifiedDate);

        AtualizaLista();

        tbData.setRowFactory( tv -> {
            TableRow<Basis> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    SingletonRowData sg = SingletonRowData.getInstance();
                    sg.RowData = row.getItem();
                    ExecutaTelaEdit();
                }
            });
            return row ;
        });
    }

    private void AtualizaLista(){

        BasisDAO bDAO = new BasisDAO() {
            @Override
            public List<Basis> SelecionaTodos(Class cls) {
                return super.SelecionaTodos(cls);
            }
        };

        obserList = FXCollections.observableList(bDAO.SelecionaTodos(classe));

        if(obserList.size() > 0)
            tbData.setItems(obserList);
        else{
            Utils.MostraAlerta("Aviso!", "Essa tabela não possui registros!", Alert.AlertType.INFORMATION);
        }
    }

    private void ExecutaTela() {
        try {
            Screens p = new Screens();
            p.setScreen(path);
            p.start(new Stage());
            AtualizaLista();
        } catch (Exception ex) {
            Utils.MostraAlerta("Erro", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void ExecutaTelaEdit() {
        try {
            if(pathEdit == null)
                throw new Exception("Não é possível editar essa classe!");
            Screens p = new Screens();
            p.setScreen(pathEdit);
            p.start(new Stage());
            AtualizaLista();
        } catch (Exception ex) {
            Utils.MostraAlerta("Erro", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void GeraEvento(){
        lblObjectName.setText("Evento");
        path = "forms/view/frCreateEvent.fxml";
        pathEdit = "forms/view/frEditEvent.fxml";

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

        TableColumn<Event, String> clEventDateStart = new TableColumn("Data Inicio");
        clEventDateStart.setCellValueFactory(f -> new ReadOnlyObjectWrapper<String>(f.getValue().getDateStart().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))));
        clEventDateStart.setMinWidth(100.0);
        clEventDateStart.setResizable(true);
        clEventDateStart.setEditable(false);

        TableColumn<Event, String> clEventDateEnd = new TableColumn("Data Final");
        clEventDateEnd.setCellValueFactory(f -> new ReadOnlyObjectWrapper<String>(f.getValue().getDateEnd().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))));
        clEventDateEnd.setMinWidth(100.0);
        clEventDateEnd.setResizable(true);
        clEventDateEnd.setEditable(false);

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

        tbData.getColumns().addAll(clEventName, clEventDescription, clEventDateStart, clEventDateEnd, clEventLocation, clEventType, clEventResponsibleMember);
    }

    private void GeraMembro(){
        lblObjectName.setText("Membro");
        path = "forms/view/frCRUDMember.fxml";
        pathEdit = "forms/view/frEditMember.fxml";

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

    private void GeraProjeto(){
        lblObjectName.setText("Projeto");
        path = "forms/view/frCRUDProject.fxml";
        pathEdit = "forms/view/frEditProject.fxml";

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

    private void GeraTarefa(){
        lblObjectName.setText("Tarefa");
        path = "forms/view/frCreateTasks.fxml";
        pathEdit = "forms/view/frEditTask.fxml";

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

        TableColumn<Task, String> clTaskDate = new TableColumn("Data de entrega");
        clTaskDate.setCellValueFactory(f -> new ReadOnlyObjectWrapper<String>(f.getValue().getDueDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
        clTaskDate.setPrefWidth(140.0);
        clTaskDate.setResizable(true);
        clTaskDate.setEditable(true);

        TableColumn<Task, Boolean> clTaskCompleted = new TableColumn("Tarefa finalizada");
        clTaskCompleted.setCellValueFactory(f -> new ReadOnlyBooleanWrapper(f.getValue().isTaskCompleted()));
        clTaskCompleted.setCellFactory(tc -> new CheckBoxTableCell<>());
        clTaskCompleted.setPrefWidth(100.0);
        clTaskCompleted.setResizable(true);
        clTaskCompleted.setEditable(true);

        TableColumn<Task, TeamMember> clTaskMember = new TableColumn("Membro");
        clTaskMember.setCellValueFactory(f -> new ReadOnlyObjectWrapper<TeamMember>(f.getValue().getAssignedTo()));
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

    private void GeraTime(){
        lblObjectName.setText("Time");
        path = "forms/view/frCRUDTeam.fxml";
        pathEdit = "forms/view/frEditTeam.fxml";

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

    private void GeraTimeMembro(){
        lblObjectName.setText("Membros de Time");
        path = "forms/view/frCRUDTeamMember.fxml";
        pathEdit = null;

        TableColumn<TeamMember, Member> clMember = new TableColumn<>("Membro");
        clMember.setCellValueFactory(f -> new ReadOnlyObjectWrapper<Member>(f.getValue().getMember()));
        clMember.setMinWidth(100.0);
        clMember.setResizable(true);
        clMember.setEditable(false);

        TableColumn<TeamMember, Team> clTeam = new TableColumn<>("Time");
        clTeam.setCellValueFactory(f -> new ReadOnlyObjectWrapper<Team>(f.getValue().getTeam()));
        clTeam.setMinWidth(100.0);
        clTeam.setResizable(true);
        clTeam.setEditable(false);

        tbData.getColumns().addAll(clMember, clTeam);
    }

    private void GeraPerfil(){
        lblObjectName.setText("Perfis");
        path = "forms/view/frCreateProfile.fxml";
        pathEdit = null;

        TableColumn<Profile, String> clProfileName = new TableColumn<>("Nome");
        clProfileName.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getName()));
        clProfileName.setMinWidth(100.0);
        clProfileName.setResizable(true);
        clProfileName.setEditable(false);

        TableColumn<Profile, String> clProfilePermission = new TableColumn<>("Permissão");
        clProfilePermission.setCellValueFactory(f -> new ReadOnlyStringWrapper(f.getValue().getPermissionLevel()));
        clProfilePermission.setMinWidth(100.0);
        clProfilePermission.setResizable(true);
        clProfilePermission.setEditable(false);

        tbData.getColumns().addAll(clProfileName, clProfilePermission);
    }

    private void GeraUsuario(){
        lblObjectName.setText("Usuario");
        path = "forms/view/frCreateUser.fxml";
        pathEdit = "forms/view/frEditUser.fxml";

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
}
