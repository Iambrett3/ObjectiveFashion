package controller;


import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import clothing.Outfit;

public class Mailer {
	//private static Outfit outfit; //does not need outfit variable
	private static String USER_NAME = "objectivefashion";  // GMail user name (just the part before "@gmail.com")
	private static String PASSWORD = "rafdidasrhot"; // GMail password
	private static DateFormat DATEFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	


	public static void sendOutfit(Outfit outfit, String warnings, String recipient) {
		//Mailer.outfit=outfit;
		String from = USER_NAME;
        String pass = PASSWORD;
        String[] to = { recipient }; // list of recipient email addresses
        Date date = new Date();
        String subject = "Here is your outfit for " + DATEFORMAT.format(date);
        String body = outfit.toString();
        body += warnings;

        sendFromGMail(from, pass, to, subject, body);
		
	}
	
//	public static void main(String[] args) {
//        String from = USER_NAME;
//        String pass = PASSWORD;
//        String[] to = { RECIPIENT }; // list of recipient email addresses
//        Date date = new Date();
//        String subject = "Here is your outfit for " + DATEFORMAT.format(date);
//        String body = "Blue shirt";
//
//        sendFromGMail(from, pass, to, subject, body);
//    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("in-v3.mailjet.com", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
	
}
