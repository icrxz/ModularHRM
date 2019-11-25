package br.com.ec6.modular.global;

import br.com.ec6.modular.model.Event;
import br.com.ec6.modular.model.Task;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class Utils {
    public static void sendEmailNotification(String emailTo, Event event){
        String body, subject;
        subject = "Novo Evento criada";
        body = "Ola "+event.getResponsibleTeamMember().getMember().getName()+"\n Houve uma atualização no Evento: "+event.getName()+
                "Descrição: "+event.getDescription()+
                "Tipo: "+event.getType()+
                "Horario Inicio: "+event.getDateStart()+
                "Horario Fim: "+event.getDateEnd()+
                "Local: "+event.getLocation();
        sendEmail(emailTo, body, subject);
    }
    public static void sendEmailNotification(String emailTo, Task event){
        String body, subject;
        subject = "Nova Tarefa criada";
        body = "Ola "+event.getAssignedTo().getMember().getName()+"\n Houve uma atualização na tarefa: "+event.getName()+
                "Descrição: "+event.getDescription()+
                "Tarefa: "+event.isTaskCompleted()+
                "Evento: "+event.getRelatedEvent().getName()+
                "Data de entrega: "+event.getDueDate();
        //body = "teste";
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
}
