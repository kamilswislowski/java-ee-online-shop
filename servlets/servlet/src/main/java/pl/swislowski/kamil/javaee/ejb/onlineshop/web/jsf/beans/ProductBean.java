package pl.swislowski.kamil.javaee.ejb.onlineshop.web.jsf.beans;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Product;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@ManagedBean//(name = "productBean")
@SessionScoped
public class ProductBean {
    private static final Logger LOGGER = Logger.getLogger(ProductBean.class.getName());
    private static final String MANAGE_PRODUCT_VIEW_NAME = "manageProduct";

    private Product product = new Product();

    private List<Product> products = new ArrayList<>();

    private boolean edit;

    @PostConstruct
    public void initialize(){
        products.add(new Product(5L, "Wiadro", BigDecimal.valueOf(19.99), 10, null));
        products.add(new Product(6L, "Lopata", BigDecimal.valueOf(15.99), 15, null));
        LOGGER.info("Initializing ...");
    }

    public String addProductView(){
        LOGGER.info("Cleaning product ...");
        product = new Product();
        this.edit = false;
        LOGGER.info("Product : " + product);
        return MANAGE_PRODUCT_VIEW_NAME;
    }

    public String addProduct() {
        LOGGER.info("Saving product : " + product);
        products.add(product);
        return "products";
    }

    public String editProductView(Long id) {
        LOGGER.info("Updating product ... with id: " + id);
        for (Product product : products) {
            Long productId = product.getId();
            if (Objects.equals(productId, id)) {
                LOGGER.info("Found product : " + product);
                this.product = product;
            }
        }
        this.edit = true;
        return MANAGE_PRODUCT_VIEW_NAME;
    }

    public String editProduct() {
        LOGGER.info("Editing product : " + product);
        for (Product product : products) {
            Long productId = product.getId();
            if (Objects.equals(productId, this.product.getId())) {
                LOGGER.info("Found product : " + product);
//                this.product = product;
                product.setName(this.product.getName());
                product.setStock(this.product.getStock());
            }
        }
//        products.add(product);
        return "products";
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

    public boolean getEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
