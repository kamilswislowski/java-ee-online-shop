package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.mdb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

//@JMSDestinationDefinition(name = JmsConstants.JMS_QUEUE_CART_QUEUE, interfaceName = "javax.jms.Queue", destinationName = JmsConstants.JMS_QUEUE_CART_QUEUE)
@MessageDriven(mappedName = JmsConstants.CART_MDB_TOPIC)
public class CartMdb implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(CartMdb.class.getName());

    @Resource(lookup = "EmailSession")
    private Session emailSession;

    @Override
    public void onMessage(Message message) {
        if (message instanceof ObjectMessage) {
            ObjectMessage objectMessage = (ObjectMessage) message;
            try {
                Serializable object = objectMessage.getObject();
                if (object instanceof ProductModel) {
                    ProductModel productModel = (ProductModel) object;
                    LOGGER.info("ProductModel from TopicMessage : " + productModel);
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {
                String text = textMessage.getText();
                LOGGER.info("Receiving Message : " + text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
//        SendEmailSMTP.send("Message!!!");
//        sendEmail("Hello from Java", "Email content", "qamilus@wp.pl");
    }

    private void sendEmail(String subject, String msg, String emailTo) {
        LOGGER.info("Sending email: " + subject);
        try {
            // Create the message object
            javax.mail.Message message = new MimeMessage(emailSession);

            // Adjust the recipients. Here we have only one
            // recipient. The recipient's address must be
            // an object of the InternetAddress class.
            message.setRecipients(javax.mail.Message.RecipientType.TO,
                    InternetAddress.parse(emailTo, false));

            // Set the message's subject
            message.setSubject(subject);

            // Insert the message's body
            message.setText(msg);

            // This is not mandatory, however, it is a good
            // practice to indicate the software which
            // constructed the message.
            message.setHeader("X-Mailer", "My Mailer");

            // Adjust the date of sending the message
            Date timeStamp = new Date();
            message.setSentDate(timeStamp);

            // Use the 'send' static method of the Transport
            // class to send the message
            Transport.send(message);
        } catch (MessagingException e) {
            LOGGER.log(Level.WARNING, "Unable to send an email", e);
        }
    }
}
