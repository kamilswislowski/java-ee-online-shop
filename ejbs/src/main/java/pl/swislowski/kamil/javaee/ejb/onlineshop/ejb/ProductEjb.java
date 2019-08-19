package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Product;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.entity.ProductEntity;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.ProductService;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Stateless
public class ProductEjb implements ProductEjbLocal {
    private static final Logger LOGGER = Logger.getLogger(ProductEjb.class.getName());

    @Inject
    private ProductService productService;

    private AtomicLong productCounter = new AtomicLong(1);

    @Schedule(second = "*/10", minute = "*", hour = "*")
    public void addProduct(){
        productService.create(new ProductEntity("Materac", BigDecimal.valueOf(99.99),10));
    }

    @Schedule(second = "*/30", minute = "*", hour = "*")
    public void removeProduct() {
        productService.delete(productCounter.getAndIncrement());
    }

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
