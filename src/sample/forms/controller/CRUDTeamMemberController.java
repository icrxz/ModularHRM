package sample.forms.controller;

import br.com.ec6.modular.contoller.MemberDAO;
import br.com.ec6.modular.contoller.TeamDAO;
import br.com.ec6.modular.contoller.TeamMemberDAO;
import br.com.ec6.modular.model.Member;
import br.com.ec6.modular.model.Team;
import br.com.ec6.modular.model.TeamMember;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import sample.Screens;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CRUDTeamMemberController implements Initializable {

    @FXML
    private ComboBox<Team> cbTime;
    @FXML
    private ComboBox<Member> cbMembro;
    @FXML
    private Button btnCriarMembro;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Membros do Time");

        CarregaComboBox();

        this.btnCriarMembro.setOnMouseClicked((MouseEvent e) -> {
            AdicionaMembroTime();
        });
    }

    private void AdicionaMembroTime(){
        try{
            TeamMember teamMember = new TeamMember();
            TeamMemberDAO tDAO = new TeamMemberDAO();
            Member member = cbMembro.getValue();
            Team team = cbTime.getValue();

            if(member != null)
                teamMember.setMember(member);
            else
                throw new Exception("Selecione um membro!");

            if(team != null)
                teamMember.setTeam(team);
            else
                throw new Exception("Selecione um time!");

            tDAO.Insere(teamMember);

            MostraAlerta("Membro do time incluído com sucesso!");
            Screens.stage.close();
        }catch(Exception ex){
            MostraAlerta(ex.getMessage());
        }
    }

    private void CarregaComboBox(){
        try {
            cbMembro.getItems().clear();
            cbTime.getItems().clear();

            MemberDAO mDAO = new MemberDAO();
            TeamDAO tDAO = new TeamDAO();

            List<Member> memberList = mDAO.SelecionaTodos(Member.class);
            List<Team> teamList = tDAO.SelecionaTodos(Team.class);

            if (memberList.size() > 0)
                cbMembro.getItems().addAll(memberList);
            else{
                throw new Exception("Nenhum membro foi encontrado!");
            }

            if (teamList.size() > 0)
                cbTime.getItems().addAll(teamList);
            else{
                throw new Exception("Nenhum time ativo foi encontrado!");
            }

        }
        catch (Exception ex){
            MostraAlerta(ex.getMessage());
        }
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
