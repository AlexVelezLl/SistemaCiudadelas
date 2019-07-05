/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session; 
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author joangie
 */
public class Mailer {
    private final String remitente = "sistemaciudadelas@gmail.com";
    private final String clave= "poo2019123"; 
    
    
public void enviarCorreo(String correo_d, String message){
    
    String destino= correo_d;
    
    Properties props= new Properties(); 
    props.put("mail.smtp.host","smtp.gmail.com"); //Servidor gmail
    props.setProperty("mail.smtp.starttls.enable","true");  //extension de protocolos para mejorar texto plano a cifrado??? 
    props.setProperty("mail.smtp.port","587"); 
    props.setProperty("mail.smtp.user",remitente); 
    props.setProperty("mail.smtp.clave",clave);
    props.setProperty("mail.smtp.auth","true");
    
    Session session= Session.getDefaultInstance(props); 
    MimeMessage mensaje= new MimeMessage(session); 
    
    try{
        mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
        mensaje.setSubject("No-reply"); 
        mensaje.setText(message);
        Transport transport = session.getTransport("smtp"); 
        transport.connect("smtp.gmail.com",remitente,clave); 
        transport.sendMessage(mensaje, mensaje.getAllRecipients());
        transport.close();  
        System.out.println("Correo Enviado");
    }catch(Exception e){
        System.out.println("Ha ingresado un correo no valido");; 
    
    }
}



  
}
    

