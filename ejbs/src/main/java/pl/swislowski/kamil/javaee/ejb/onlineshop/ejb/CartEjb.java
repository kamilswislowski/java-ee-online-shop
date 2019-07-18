package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Order;

import javax.ejb.Stateful;
import java.util.logging.Logger;

@Stateful
public class CartEjb implements CartEjbRemote {
    private static final Logger LOGGER = Logger.getLogger(CartEjb.class.getName());

    public CartEjb() {
    }

    public void add() {

    }

    public void checkout(Order order) {
        LOGGER.info("Checking out order " + order);
    }
}
