package org.oracle.cloud.reports;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email 
{

	
	public void sendMail(String from,String To)
	{
		//Local Host
		String host="localhost";
		
		//Get the session object  
		Properties properties=System.getProperties();
		properties.setProperty("mail.smtp.host", host);  
	    Session session = Session.getDefaultInstance(properties);
		
		//Compose the message
	    try 
	    {
	    	MimeMessage message=new MimeMessage(session);
	    	message.setFrom(new InternetAddress(from));
	    	message.addRecipient(Message.RecipientType.TO, new InternetAddress(To));
	    	message.setSubject(" This is the demo Mail");
	    	
	    	//Now set the actual message
	    	message.setText("Demo text message");
	    	
	    	//Send Message
	    	Transport.send(message);
	    	System.out.println("Message sent sucessfully... ");	    	
		} 
	    catch (MessagingException  e)
	    {
			// TODO: handle exception
	    	e.printStackTrace();
		}
	    
	    
	    
		
		
	}
	
}
