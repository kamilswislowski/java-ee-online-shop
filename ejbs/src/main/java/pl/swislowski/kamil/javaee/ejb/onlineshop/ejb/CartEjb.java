package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Order;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

@Stateful
public class CartEjb implements CartEjbRemote {
    private static final Logger LOGGER = Logger.getLogger(CartEjb.class.getName());

    @Inject
    private CategoryCacheEjbLocal categoryCacheEjbLocal;

    public CartEjb() {
    }

    @PostConstruct
    public void initialize(){
        LOGGER.info("Initializing...");
        List<Category> categories = categoryCacheEjbLocal.categories();
        LOGGER.info("Kategorie : " + categories);
    }

    public void add() {
    }

    public void checkout(Order order) {
        LOGGER.info("Checking out order " + order);
    }
}
