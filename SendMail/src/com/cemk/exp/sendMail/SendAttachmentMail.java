package com.cemk.exp.sendMail;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

public class SendAttachmentMail {

	private static SendAttachmentMail _instance = new SendAttachmentMail();
	private static final Logger logger = Logger.getLogger(SendAttachmentMail.class);
	public static SendAttachmentMail getInstance() {
		return _instance;
	}

	public void sendAttachmentEmail(String[] recipients, String text,
			String fileName) {
		String login = SendMailConstants.login;
		String password = SendMailConstants.password;
		String to = "";
		String subject = SendMailConstants.backUpSubject;
		Properties props = new Properties();
		props.setProperty(SendMailConstants.host, SendMailConstants.hostName);
		props.setProperty(SendMailConstants.smtp, SendMailConstants.smtpPort);
		props.setProperty(SendMailConstants.smtpAuth,
				SendMailConstants.smtpAuthBoolean);
		props.setProperty(SendMailConstants.smtpStarttls,
				SendMailConstants.smtpStarttlsBoolean);
		try {
			Authenticator auth = new SMTPAuthenticator(login, password);
			Session session = Session.getInstance(props, auth);

			// create a message
			Message message = new MimeMessage(session);
			// Sender's email ID needs to be mentioned
			String from = SendMailConstants.from;
			// set the from and to address
			InternetAddress addressFrom = new InternetAddress(from);
			message.setFrom(addressFrom);

			InternetAddress[] addressTo = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++) {
				addressTo[i] = new InternetAddress(recipients[i]);
				if (to == "")
					to = recipients[i];
				else
					to = to + "," + recipients[i];
			}
			message.setRecipients(Message.RecipientType.TO, addressTo);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set Subject: header field
			message.setSubject(subject);

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setText(text);

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(fileName);
			messageBodyPart.setDataHandler(new DataHandler(source));
			File attachment=new File(fileName);
			messageBodyPart.setFileName(attachment.getName());
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);
			logger.info("+--------------------------------------+");
			logger.info("| Data Backup email sent successfully  |");
			logger.info("+--------------------------------------+");
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			logger.info("+--------------------------------------+");
			logger.info("|   Backup email sending failed        |");
			logger.info("+--------------------------------------+");
			mex.printStackTrace();
		}
	}

}
