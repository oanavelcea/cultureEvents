package fr.dawan.cultureEvents.tools;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

public class EmailTools {

	private static final String SMTP_SERVER = "smtp.dsl.ovh.net";
	private static final String EMAIL_SENDER = "noreply@gmail.com";
	
	public static void sendEmail(String to, String subject, String msg) throws Exception {
		Email email = new SimpleEmail();
		email.setHostName(SMTP_SERVER);
		//email.setHostName("smtp.googlemail.com");
		
		
//		email.setSmtpPort(25);
//		email.setAuthenticator(new DefaultAuthenticator("username", "password"));
//		email.setSSLOnConnect(true);
		email.setFrom(EMAIL_SENDER);
		email.setSubject(subject);
		email.setMsg(msg);
		email.addTo(to);
		email.send();
	}
}
