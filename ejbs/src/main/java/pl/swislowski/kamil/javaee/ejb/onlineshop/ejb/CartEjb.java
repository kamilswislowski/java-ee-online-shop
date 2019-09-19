package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.entity.CategoryEntity;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.entity.ProductEntity;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.mdb.JmsConstants;
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
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
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

    @Inject
    private CategoryCacheEjbLocal categoryCacheEjbLocal;

    @Inject
    private CategoryService categoryService;

    @Inject
    private ProductService productService;

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
    }

    @Override
    public void readQueue(){
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
