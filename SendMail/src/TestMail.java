import java.util.Properties;

import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.cemk.exp.sendMail.SMTPAuthenticator;
import com.cemk.exp.sendMail.SendAttachmentMail;
import com.cemk.exp.sendMail.SendMailConstants;

public class TestMail {

	public static boolean sendEmail(String[] recipients, String message,
			String type) {
		String login = SendMailConstants.login;
		String password = SendMailConstants.password;
		String from = SendMailConstants.from;
		String to = "";
		String subject = "";
		if (type.equals("email"))
			subject = SendMailConstants.subject;
		else if (type.equals("feedback"))
			subject = SendMailConstants.feedback;
		else
			subject = SendMailConstants.changePwd;
		String finalMessage = message;
		try {
			Properties props = new Properties();
			props.setProperty(SendMailConstants.host,
					SendMailConstants.hostName);
			props.setProperty(SendMailConstants.smtp,
					SendMailConstants.smtpPort);
			props.setProperty(SendMailConstants.smtpAuth,
					SendMailConstants.smtpAuthBoolean);
			props.setProperty(SendMailConstants.smtpStarttls,
					SendMailConstants.smtpStarttlsBoolean);

			Authenticator auth = new SMTPAuthenticator(login, password);
			Session session = Session.getInstance(props, auth);

			// create a message
			Message msg = new MimeMessage(session);

			// set the from and to address
			InternetAddress addressFrom = new InternetAddress(from);
			msg.setFrom(addressFrom);

			InternetAddress[] addressTo = new InternetAddress[recipients.length];
			for (int i = 0; i < recipients.length; i++) {
				addressTo[i] = new InternetAddress(recipients[i]);
				if (to == "")
					to = recipients[i];
				else
					to = to + "," + recipients[i];
			}
			msg.setRecipients(Message.RecipientType.TO, addressTo);

			// Optional : You can also set your custom headers in the Email if
			// you Want
			msg.addHeader("MyHeaderName", "myHeaderValue");

			// Setting the Subject and Content Type
			msg.setSubject(subject);
			msg.setContent(finalMessage, "text/html");
			Transport.send(msg);
			System.out.println("Email has been succesfully send to " + to);
			return true;
		} catch (AuthenticationFailedException ex) {
			System.out.println("Authentication failed");
			return false;

		} catch (AddressException ex) {
			System.out.println("Wrong email address");
			return false;

		} catch (MessagingException ex) {
			System.out.println(ex.getMessage());
			return false;
		}

	}

	public static void main(String[] args) {
		String[] recipients = { "cemkabhijit@gmail.com" };
		String message = "Hi User<br>"
				+ "<br>"
				+ "<font color='blue'>This email has been sent to you to verify that<br>"
				+ "Attached Mail sending through Java is possible</font><br>"
				+ "<br>"
				+ "<font color='red'>Regards.<br>"
				+ "Ihalkhata Calbook</font>"
				+ "<br>"
				+ "<br>"
				+ "<br>"
				+ "<font color='green'>Think...before you print. Save paper, save trees, save the planet</font>";
		//sendEmail(recipents, message, "email");
		String fileName="C:/Users/Abhijit/Desktop/Abhijit_roy_updated.pdf";
		SendAttachmentMail.getInstance().sendAttachmentEmail(recipients, message, fileName);
	}

}
