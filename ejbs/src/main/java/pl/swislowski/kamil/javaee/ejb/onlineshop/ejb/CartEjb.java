package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.entity.CategoryEntity;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.entity.ProductEntity;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductItemModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.mdb.JmsConstants;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.CartService;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.CategoryService;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.ProductService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateful
public class CartEjb implements CartEjbRemote { // Wywoływane z klienta (oddzielny projekt w intellij), ponieważ ejb jest @Remote
    private static final Logger LOGGER = Logger.getLogger(CartEjb.class.getName());

    @Inject
    private JMSContext jmsContext;

    @Resource(lookup = JmsConstants.CART_MDB_TOPIC)
    private Topic cartTopic;

    @Resource(lookup = JmsConstants.CART_MDB_QUEUE)
    private Queue cartQueue;

    //    @Resource(lookup = "EmailSession")
    @Resource(lookup = "NetWaveEmailSession")
    private Session emailSession;

    @Inject
    private CategoryCacheEjbLocal categoryCacheEjbLocal;

    @Inject
    private CategoryService categoryService;

    @Inject
    private ProductService productService;

    @Inject
    private CartService cartService;

    private List<ProductModel> productModels;

    private Random random = new Random();

    public CartEjb() {
    }

    @PostConstruct
    public void initialize() {
        LOGGER.info("Initializing...");
        List<Category> categories = categoryCacheEjbLocal.categories();
        LOGGER.info("Kategorie : " + categories);
        LOGGER.info("#### JMSContext : " + jmsContext);
//        LOGGER.info("$$$$ Queue : " + checkoutQueue);
    }

    public void add() { // Wywoływane z klienta (oddzielny projekt w intellij)

        CategoryEntity categoryTekstylia = categoryService.create(new CategoryEntity("Tekstylia"));
        LOGGER.info("Created CategoryEntity : " + categoryTekstylia);
        CategoryEntity categoryZaslony = categoryService.create(new CategoryEntity("Zaslony"));
        LOGGER.info("Created CategoryEntity : " + categoryZaslony);
        CategoryEntity categoryDom = categoryService.create(new CategoryEntity("Dom"));
        LOGGER.info("Created CategoryEntity : " + categoryDom);

        ProductEntity productCzarneZaslony = new ProductEntity("Czarne zaslony", BigDecimal.valueOf(9.99), 30);
//        productService.create(productCzarneZaslony);
        Set<CategoryEntity> categories = new HashSet<>();
        categories.add(categoryTekstylia);
        categories.add(categoryZaslony);
        categories.add(categoryDom);
        productCzarneZaslony.setCategories(categories);
    }

    public void checkout(ProductModel productModel) {
        LOGGER.info("Checking out !!!");
        productService.list();
        categoryService.list();

        LOGGER.info("Checking out!");
        TextMessage textMessage = jmsContext.createTextMessage("MESSAGE ####################################################################");
        JMSProducer producer = jmsContext.createProducer();
        producer.send(cartTopic, textMessage);

        TextMessage queueTextMessage = jmsContext.createTextMessage("QueueTextMessage #################### :" + random.nextInt());
        producer.send(cartQueue, queueTextMessage);

        ObjectMessage objectMessage = jmsContext.createObjectMessage(productModel);
        producer.send(cartTopic, objectMessage);

//        SendEmailSMTP.send("First email message.");

        sendEmail("Hello from Java", "Email content", "qamilus@wp.pl");
    }

    @Override
    public ProductItemModel updateProductItemAmount(Long id, boolean addToCart) {
        return cartService.updateProductItemAmount(id, addToCart);
    }

    private void sendEmail(String subject, String msg, String emailTo) {
        LOGGER.info("Sending email: " + subject);
        try {
            // Create the message object
            javax.mail.Message message = new MimeMessage(emailSession);
            InternetAddress[] addresses = InternetAddress.parse("online.shop@jacekservices01.dbox.pl", false);
            message.setFrom(addresses[0]);

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

//    public void sendEmail(String to, String subject, String body) {
//        MimeMessage message = new MimeMessage(emailSession);
//        try {
//            message.setFrom(new InternetAddress(emailSession.getProperty("mail.from")));
//            InternetAddress[] address = {new InternetAddress(to)};
//            message.setRecipients(Message.RecipientType.TO, address);
//            message.setSubject(subject);
//            message.setSentDate(new Date());
//            message.setText(body);
//            Transport.send(message);
//        } catch (MessagingException ex) {
//            ex.printStackTrace();
//        }
//    }

    @Override
    public void readQueue() {
        //NOTE: Ostrożnie z samodzielnym odbieraniem wiadomości w sposób synchroniczny.
//        JMSConsumer consumer = jmsContext.createConsumer(cartQueue);
//        TextMessage receive = (TextMessage)consumer.receive();
//        try {
//            LOGGER.info("Received message from Queue : " + receive.getText());
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
    }
}
