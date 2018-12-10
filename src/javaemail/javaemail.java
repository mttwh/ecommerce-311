package javaemail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class javaemail {

	
	public void sendEmail(String recipient, String subject, String body) {
		String username = "ist402group3brandywine@gmail.com";
		String password = "psu_brandywine";
		
		Properties props = System.getProperties();
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", username);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress(username));
			InternetAddress toAddress = new InternetAddress(recipient);
			message.addRecipient(Message.RecipientType.TO, toAddress);
			
			message.setSubject(subject);
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			System.out.println("Email successfully sent");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
