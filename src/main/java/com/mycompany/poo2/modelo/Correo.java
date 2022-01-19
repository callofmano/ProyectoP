package com.mycompany.poo2.modelo;

package espol.poo.empadminjar;

import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.*;
/**
 *
 * @author User
 */
public class Correo {
    
    public static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
        // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
         //Para la dirección nomcuenta@gmail.com
        //pooespol@gmail.com
        String emisor="pooespol@gmail.com ";
        String clave = "FIEC_2022";
        
        
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
        props.put("mail.smtp.user", emisor);
        props.put("mail.smtp.clave", "FIEC_2022");    //La clave de la cuenta
        props.put("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
        props.put("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
        props.put("mail.smtp.port", "587"); //El puerto SMTP seguro de Google
    
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
    
        try {
            message.setFrom(new InternetAddress(emisor));
            message.addRecipients(Message.RecipientType.TO, destinatario);
            //message.addRecipient(Message.RecipientType.TO, destinatario);   //Se podrían añadir varios de la misma manera
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", emisor, clave);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (MessagingException me) {
            me.printStackTrace();   //Si se produce un error
        }
    }
    
}