package sample.forms.controller;

import br.com.ec6.modular.contoller.MemberDAO;
import br.com.ec6.modular.model.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import sample.Screens;

import java.net.URL;
import java.util.ResourceBundle;

public class CRUDMemberController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtCargo;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPhone;
    @FXML
    private Button btnCriarMembro;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Membros");

        this.btnCriarMembro.setOnMouseClicked((MouseEvent e) -> {
            AdicionaMembro();
        });

        this.btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            Screens.stage.close();
        });

    }

    private void AdicionaMembro(){
        try{
            Member member = new Member();
            MemberDAO mDAO = new MemberDAO();

            String nome = txtNome.getText().trim();
            String cargo = txtCargo.getText().trim();
            String email = txtEmail.getText().trim();
            String telefone = txtPhone.getText().trim();

            if(nome.length() > 0)
                member.setName(nome);
            else
                throw new Exception("Digite um nome!");
            if(email.length() > 0)
                member.setEmail(email);
            else
                throw new Exception("Digite um email!");
            if(cargo.length() > 0)
                member.setRole(cargo);
            else
                throw new Exception("Digite um cargo!");
            if(telefone.length() > 0)
                member.setPhone(telefone);
            else
                throw new Exception("Digite um telefone!");

            mDAO.Insere(member);

            MostraAlerta("Membro cadastrado com sucesso!");
            Screens.stage.close();
        }catch(Exception ex){
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
