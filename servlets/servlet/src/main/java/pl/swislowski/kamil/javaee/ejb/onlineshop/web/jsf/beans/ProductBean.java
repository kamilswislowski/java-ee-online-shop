package pl.swislowski.kamil.javaee.ejb.onlineshop.web.jsf.beans;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.ProductEjbLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
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

    @EJB
    private ProductEjbLocal productEjbLocal;

    private ProductModel productModel = new ProductModel();

    private List<ProductModel> productModels = new ArrayList<>();

    private boolean edit;

    @PostConstruct
    public void initialize(){
        productModels.add(new ProductModel(5L, "Wiadro", BigDecimal.valueOf(19.99), 10, null));
        productModels.add(new ProductModel(6L, "Lopata", BigDecimal.valueOf(15.99), 15, null));
        LOGGER.info("Initializing ...");
    }

    public String addProductView(){
        LOGGER.info("Cleaning productModel ...");
        productModel = new ProductModel();
        this.edit = false;
        LOGGER.info("ProductModel : " + productModel);
        return MANAGE_PRODUCT_VIEW_NAME;
    }

    public String addProduct() {
        LOGGER.info("Saving productModel : " + productModel);
        productModels.add(productModel);
        productEjbLocal.create(productModel);
        return "productModels";
    }

    public String editProductView(Long id) {
        LOGGER.info("Updating productModel ... with id: " + id);
        for (ProductModel productModel : productModels) {
            Long productId = productModel.getId();
            if (Objects.equals(productId, id)) {
                LOGGER.info("Found productModel : " + productModel);
                this.productModel = productModel;
            }
        }
        this.edit = true;
        return MANAGE_PRODUCT_VIEW_NAME;
    }

    public String editProduct() {
        LOGGER.info("Editing productModel : " + productModel);
        for (ProductModel productModel : productModels) {
            Long productId = productModel.getId();
            if (Objects.equals(productId, this.productModel.getId())) {
                LOGGER.info("Found productModel : " + productModel);
//                this.productModel = productModel;
                productModel.setName(this.productModel.getName());
                productModel.setStock(this.productModel.getStock());
            }
        }
//        productModels.add(productModel);
        return "productModels";
    }

    public void delete(ProductModel productModel) {
        LOGGER.info("Deleting productModel ... with id: " + productModel);
        productModels.remove(productModel);
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    public List<ProductModel> getProductModels() {
        return productModels;
    }

    public void setProductModels(List<ProductModel> productModels) {
        this.productModels = productModels;
    }

    public boolean getEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }
}
