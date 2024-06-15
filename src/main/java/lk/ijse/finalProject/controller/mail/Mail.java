package lk.ijse.finalProject.controller.mail;

import javafx.scene.control.Alert;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


public class Mail {
    public static void setMail(String email, String tittle, String body) {
        final String userName = "prisonsystem737@gmail.com";
        final String password = "vtgrticiqeblrxdi";
        //final String userName = "sahandanujaya48@gmail.com";
        //final String password = "95334238Sd$";
        final String toEmail = "sahandanujaya48@gmail.com";

        System.out.println("Start");
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(userName, password);

            }
        });

        try{
            MimeMessage message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail, true));
            message.setSubject(tittle);
            message.setText(body);
            new Alert(Alert.AlertType.INFORMATION,"Sending....").showAndWait();
            Transport.send(message);
            new Alert(Alert.AlertType.CONFIRMATION,"Send successfully").show();

        }catch (MessagingException me){
            System.out.println("Exception: "+me);

        }
    }
}