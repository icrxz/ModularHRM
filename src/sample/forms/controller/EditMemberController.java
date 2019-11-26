package sample.forms.controller;

import br.com.ec6.modular.contoller.MemberDAO;
import br.com.ec6.modular.global.SingletonRowData;
import br.com.ec6.modular.global.Utils;
import br.com.ec6.modular.model.Member;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Screens;

import java.net.URL;
import java.util.ResourceBundle;

public class EditMemberController implements Initializable {
    private Member mRow;
    private Stage janela;

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
        mRow = (Member) SingletonRowData.RowData;
        janela = Screens.stage;
        Screens.stage.setResizable(false);
        Screens.stage.setMaximized(false);
        Screens.stage.setTitle("Modular HRM - Editar membro");

        txtCargo.setText(mRow.getRole());
        txtEmail.setText(mRow.getEmail());
        txtNome.setText(mRow.getName());
        txtPhone.setText(mRow.getPhone());

        this.btnCriarMembro.setOnMouseClicked((MouseEvent e) -> {
            EditaMembro();
        });

        this.btnCancelar.setOnMouseClicked((MouseEvent e) -> {
            janela.close();
        });
    }

    private void EditaMembro(){
        try{
            MemberDAO mDAO = new MemberDAO();

            String nome = txtNome.getText().trim();
            String cargo = txtCargo.getText().trim();
            String email = txtEmail.getText().trim();
            String telefone = txtPhone.getText().trim();

            if(nome.length() > 0)
                mRow.setName(nome);
            else
                throw new Exception("Digite um nome!");
            if(email.length() > 0)
                mRow.setEmail(email);
            else
                throw new Exception("Digite um email!");
            if(cargo.length() > 0)
                mRow.setRole(cargo);
            else
                throw new Exception("Digite um cargo!");
            mRow.setPhone(telefone);

            mDAO.Altera(mRow);

            Utils.MostraAlerta("Sucesso!", "Membro cadastrado com sucesso!", Alert.AlertType.INFORMATION);
            janela.close();
        }catch(Exception ex){
            Utils.MostraAlerta("Erro!", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
