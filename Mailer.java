import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {
	
	public static void sendMessage(String subject,String text,String destinataire,String copyDest) {
		
		System.out.println("preparing to send the email");
		
		// create the session 
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.starttls.enable", "true");
		/* comment it if you want to have more execution logs*/
		//props.setProperty("mail.debug", "true");
		props.setProperty("mail.smtp.user", "gmail address");
	    
	    Session session = Session.getInstance(props);
	    
	    // create the message
	    
	    MimeMessage message = new MimeMessage(session);
	    try {
	    	message.setText(text);
	    	message.setSubject(subject);
	    	message.addRecipients(Message.RecipientType.TO, destinataire);
	    	message.addRecipients(Message.RecipientType.CC, copyDest);
	    }catch(MessagingException e) {
	    	e.printStackTrace();
	    }
	    // sending the message
	    
	    Transport trans = null;
	    try {
	    	trans = session.getTransport("smtp");
	    	trans.connect("gmail address","gmail password");
	    	trans.sendMessage(message, new Address[] {new InternetAddress(destinataire), new InternetAddress(copyDest)});
	    	System.out.println("Email sent successfully ");
	    }catch(MessagingException e) {
	    	e.printStackTrace();
	    }finally {
	    	try {
	    		if(trans!=null) {
	    			trans.close();
	    		}
	    	}catch(Exception e) {
	    		e.printStackTrace();
	    	}
	    }
	}
	

	
	public static void main(String[] args) {
		sendMessage("Test javamail","I'm testing java mail API","sende's address","reciever's address");
	}
}
