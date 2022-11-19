/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HelperClasses;

import java.io.File;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class EmailManager {
    public static void sentEmail(String from,String to,String subject,String message,boolean attachment,String attachmentPath){
        Properties properties=System.getProperties();
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");
        
        System.out.println("TO : "+to+" From : "+from+" Subject : "+subject);
        //step 1 get the session object
        Session session=Session.getInstance(properties, new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
             
                return new PasswordAuthentication("temp@gmail.com","y4vsdf3446y");
            }
            
        });
        
        session.setDebug(true);
        
        //step 2 set message
        MimeMessage mimeMessage=new MimeMessage(session);
        try{
            mimeMessage.setFrom(from);
            mimeMessage.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            mimeMessage.setSubject(subject);
            if(attachment){
                //if attachemt is attach
                MimeMultipart mimeMultipart=new MimeMultipart();
                
                MimeBodyPart text=new MimeBodyPart();
                MimeBodyPart file=new MimeBodyPart();
                try{
                    text.setText(message);
                    file.attachFile(new File(attachmentPath));
                    mimeMultipart.addBodyPart(text);
                    mimeMultipart.addBodyPart(file);
                    //set tyhe message
                    mimeMessage.setContent(mimeMultipart);
                }catch(Exception e){
                    e.printStackTrace();
                }
                
            }else{
                //simple text message
               mimeMessage.setContent(message,"text/html");  
            }
           
            
              
        //step 3 sent Message
        Transport.send(mimeMessage);
        System.out.println("Message successfuly sent........");
    
        }catch(Exception e){
            System.out.println("message not sent");
            e.printStackTrace();
        }
    }    
    
    
 
}
