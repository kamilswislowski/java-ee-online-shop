package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

public class SendEmailSMTP {

    private static final Logger LOGGER = Logger.getLogger(SendEmailSMTP.class.getName());

    // for example, smtp.mailgun.org
    private static final String SMTP_SERVER = "smtp.poczta.onet.pl";
    private static final String USERNAME = "online.shop@onet.eu";
    private static final String PASSWORD = "OnlineShop2@19";

    private static final String EMAIL_FROM = "online.shop@onet.eu";
    private static final String EMAIL_TO = "qamilus@wp.pl";
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP";
    private static final String EMAIL_TEXT = "Hello Java Mail \n ABC123";

    public static void send(String text) {
        LOGGER.info("Sending email: " + text);

        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
//        prop.put("mail.smtp.socketFactory.port", "465");
//        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.ssl.trust", SMTP_SERVER);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "465");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME,PASSWORD);
            }
        });
        Message msg = new MimeMessage(session);

        try {

            // from
            msg.setFrom(new InternetAddress(EMAIL_FROM));

            // to
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));

            // cc
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));

            // subject
            msg.setSubject(EMAIL_SUBJECT);

            // content
            msg.setText(EMAIL_TEXT + text);

            msg.setSentDate(new Date());

            // Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

            // connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);

            // send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}