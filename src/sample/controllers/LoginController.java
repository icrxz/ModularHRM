package sample.controllers;

import br.com.ec6.modular.dao.ProfilesDAO;
import br.com.ec6.modular.dao.UsersDAO;
import br.com.ec6.modular.entities.Profile;
import br.com.ec6.modular.entities.User;
import javafx.scene.control.Alert;

public class LoginController {

    public LoginController(){
        /*try{
        Teste();}
        catch(Exception ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ex.getMessage());
            alert.show();
        }*/
    }

    public void Teste() {
        ProfilesDAO pDao = new ProfilesDAO();
        UsersDAO uDao = new UsersDAO();
        Profile p = new Profile();
        User u = new User();
        p.setName("Desenvolvedor");
        p.setPermissionLevel("Desenvolvedor");

        pDao.Insere(p);

        u.setName("Modular");
        u.setEmail("teste@gmail.com");
        u.setPassword("123456");
        u.setUserName("modular");

        uDao.Insere(u);

    }
}
