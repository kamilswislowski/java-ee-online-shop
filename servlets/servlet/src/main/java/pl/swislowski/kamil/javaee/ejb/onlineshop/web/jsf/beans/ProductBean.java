package pl.swislowski.kamil.javaee.ejb.onlineshop.web.jsf.beans;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Product;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean//(name = "productBean")
@SessionScoped
public class ProductBean {
    private static final Logger LOGGER = Logger.getLogger(ProductBean.class.getName());

    private Product product = new Product();

    private List<Product> products = new ArrayList<>();

    @PostConstruct
    public void initialize(){
        products.add(new Product(5L, "Wiadro", BigDecimal.valueOf(19.99), 10, null));
        products.add(new Product(6L, "Lopata", BigDecimal.valueOf(15.99), 15, null));
        LOGGER.info("Initializing ...");
    }

    public String addProduct(){
        LOGGER.info("Cleaning product ...");
        product = new Product();
        LOGGER.info("Product : " + product);
        return "addProduct";
    }

    public String create() {
        LOGGER.info("Saving product : " + product);
        products.add(product);
        return "products";
    }

    public String update(Long id) {
        LOGGER.info("Updating product ... with id: " + id);
        return "updateProduct";
    }

    public void delete(Product product) {
        LOGGER.info("Deleting product ... with id: " + product);
        products.remove(product);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
