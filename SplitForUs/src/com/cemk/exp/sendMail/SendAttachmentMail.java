package com.cemk.exp.sendMail;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendAttachmentMail {
	
	public static void sendEmail(String []recipients,String message,String fileName){
			String login = SendMailConstants.login;
	        String password = SendMailConstants.password;
	        String from = SendMailConstants.from;
	        String to = "";
	        String subject = SendMailConstants.subject;
	        try {
	            Properties props = new Properties();
	            props.setProperty(SendMailConstants.host, SendMailConstants.hostName);
	            props.setProperty(SendMailConstants.smtp,SendMailConstants.smtpPort);
	            props.setProperty(SendMailConstants.smtpAuth, SendMailConstants.smtpAuthBoolean);
	            props.setProperty(SendMailConstants.smtpStarttls, SendMailConstants.smtpStarttlsBoolean);

	            Authenticator auth = new SMTPAuthenticator(login, password);
	            Session session = Session.getInstance(props, auth);
	            
	            // create a message
	            Message msg = new MimeMessage(session);

	            // set the from and to address
	            InternetAddress addressFrom = new InternetAddress(from);
	            msg.setFrom(addressFrom);

	            InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
	            for (int i = 0; i < recipients.length; i++)
	            {
	                addressTo[i] = new InternetAddress(recipients[i]);
	                if(to=="")
		            	to=recipients[i];
		            else
		            	to=to+","+recipients[i];
	            }
	            msg.setRecipients(Message.RecipientType.TO, addressTo);
	           

	            // Optional : You can also set your custom headers in the Email if you Want
	            msg.addHeader("MyHeaderName", "myHeaderValue");

	            // Setting the Subject and Content Type
	            msg.setSubject(subject);
	                      
	            
	            // create and fill the first message part
	    	    MimeBodyPart mbp1 = new MimeBodyPart();
	    	    mbp1.setContent(message,"text/html");
	    	    
	    	    // create the second message part
	    	    MimeBodyPart mbp2 = new MimeBodyPart();

	    	    // attach the file to the message
	    	    mbp2.attachFile(fileName);

	    	    Multipart mp = new MimeMultipart();
	    	    mp.addBodyPart(mbp1);
	    	    mp.addBodyPart(mbp2);

	    	    // add the Multipart to the message
	    	    msg.setContent(mp,"text/html");

	    	    // set the Date: header
	    	    msg.setSentDate(new Date());
     
	            Transport.send(msg);
	            System.out.println("Email has been succesfully send to "+to);
	        }
	        catch (AuthenticationFailedException ex) {
	            System.out.println("Authentication failed");


	        } catch (AddressException ex) {
	        	System.out.println( "Wrong email address");

	            
	        } catch (MessagingException ex) {
	        	System.out.println( ex.getMessage());
	        }
	        catch (IOException ioex) {
	    	    ioex.printStackTrace();
	    	}

}
}
