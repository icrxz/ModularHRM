package br.com.ec6.modular.global;

import br.com.ec6.modular.model.Event;
import br.com.ec6.modular.model.Task;
import javafx.scene.control.Alert;

import java.time.format.DateTimeFormatter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javafx.scene.control.Alert.AlertType;

public class Utils {
    public static void sendEmailNotification(String emailTo, Event event, Boolean isInsert){
        String body, subject;
        subject = "Novo Evento" +(isInsert ? " criado." : " atualizado.");
        body = "Ola "+event.getResponsibleTeamMember().getMember().getName()+"\n\nHouve uma atualização no Evento: "+event.getName()+
                "\n\nDescrição: "+event.getDescription()+
                "\nTipo: "+event.getType()+
                "\nHorario Inicio: "+event.getDateStart().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))+
                "\nHorario Fim: "+event.getDateEnd().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))+
                "\nLocal: "+event.getLocation();
        sendEmail(emailTo, body, subject);
    }
    public static void sendEmailNotification(String emailTo, Task task, Boolean isInsert){
        String body, subject;
        subject = "Nova Tarefa" +(isInsert ? " criada." : " atualizada.");
        body = "Ola "+task.getAssignedTo().getMember().getName()+"!\n\nHouve uma atualização na tarefa: "+task.getName()+
                "\n\nDescrição: "+task.getDescription()+
                "\nTarefa: "+task.isTaskCompleted()+
                "\nEvento: "+task.getRelatedEvent().getName()+
                "\nData de entrega: "+task.getDueDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        sendEmail(emailTo, body, subject);
    }
    private static void sendEmail(String toEmail, String body, String subject){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("modularsolutions7@gmail.com",
                                "Modular123");
                    }
                });

        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("modularsolutions7@gmail.com"));

            message.setRecipients(Message.RecipientType.TO,  InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void MostraAlerta(String Header, String Message, AlertType type)
    {
        Alert alert = new Alert(type);
        alert.setTitle(Header);
        alert.setContentText(Message);
        alert.getDialogPane().requestFocus();
        alert.getDialogPane().toFront();
        alert.showAndWait();
    }
}
