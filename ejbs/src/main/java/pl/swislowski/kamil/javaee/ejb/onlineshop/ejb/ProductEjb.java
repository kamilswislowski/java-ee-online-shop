package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Product;

import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ProductEjb implements ProductEjbLocal {
    private static final Logger LOGGER = Logger.getLogger(ProductEjb.class.getName());

    @Override
    public void create(Product product) {
        LOGGER.info("Creating product " + product);
    }

    @Override
    public Product read(Long id) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Product> list() {
        return null;
    }
}
