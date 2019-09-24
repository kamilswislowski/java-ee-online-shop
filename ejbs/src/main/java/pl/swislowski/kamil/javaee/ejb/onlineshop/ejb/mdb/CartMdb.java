package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.mdb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import java.io.Serializable;
import java.util.logging.Logger;

//@JMSDestinationDefinition(name = JmsConstants.JMS_QUEUE_CART_QUEUE, interfaceName = "javax.jms.Queue", destinationName = JmsConstants.JMS_QUEUE_CART_QUEUE)
@MessageDriven(mappedName = JmsConstants.CART_MDB_TOPIC)
public class CartMdb implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(CartMdb.class.getName());

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
//                SendEmailSMTP.send(text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
