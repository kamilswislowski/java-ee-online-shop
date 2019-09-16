package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.mdb;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Logger;

//@JMSDestinationDefinition(name = JmsConstants.JMS_QUEUE_CART_QUEUE, interfaceName = "javax.jms.Queue", destinationName = JmsConstants.JMS_QUEUE_CART_QUEUE)
@MessageDriven(mappedName = JmsConstants.CART_MDB_TOPIC)
//        activationConfig = {
//        @ActivationConfigProperty(propertyName = "acknowledgeMode",
//                propertyValue = "Auto-acknowledge"),
//        @ActivationConfigProperty(propertyName = "destinationType",
//                propertyValue = "javax.jms.Queue")
//})
public class CartMdb implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(CartMdb.class.getName());

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            LOGGER.info("Receiving Message : " + textMessage);
        }

    }
}
