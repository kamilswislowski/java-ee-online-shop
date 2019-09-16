package pl.swislowski.kamil.javaee.ejb.onlineshop.web.jsf.beans;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.CartEjbRemote;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.CategoryCacheEjbLocal;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.ProductEjbLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//@RequestScoped
//@Named
@ManagedBean//(name = "cartJsfBean")
@SessionScoped
public class CartBean implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(CartBean.class.getName());

    @EJB
    private CartEjbRemote cartEjbRemote;

    @EJB
    private CategoryCacheEjbLocal categoryCacheEjbLocal;

    @EJB
    private ProductEjbLocal productEjbLocal;

    private String name;

    private List<ProductModel> productModels = new ArrayList<>();

    public CartBean() {
    }

    @PostConstruct
    public void initialize() {
        LOGGER.info("Initializing ...");
    }

    public String addProductToCart(Long id) {
        ProductModel productModel = productEjbLocal.read(id);
        LOGGER.info("Adding product to cart : " + productModel);
        productModels.add(productModel);
        return "cart";
    }

    public void delete(ProductModel productModel) {
        LOGGER.info("Deleting productModel ... with id: " + productModel);
        productModels.remove(productModel);
    }

    public String checkout() {
        LOGGER.info("Checking out...");
//        cartEjbRemote.checkout(new Order(new ArrayList<>()));
        cartEjbRemote.checkout();
        categoryCacheEjbLocal.categories();

//        return "hello";
        return null;
    }


//    public String addProductView(){
//        LOGGER.info("Cleaning productModel ...");
//        productModel = new ProductModel();
//        this.edit = false;
//        LOGGER.info("ProductModel : " + productModel);
//        return MANAGE_PRODUCT_VIEW_NAME;
//    }

//    public String editProductView(Long id) {
//        LOGGER.info("Updating productModel ... with id: " + id);
//        for (ProductModel productModel : productModels) {
//            Long productId = productModel.getId();
//            if (Objects.equals(productId, id)) {
//                LOGGER.info("Found productModel : " + productModel);
//                this.productModel = productModel;
//            }
//        }
//        this.edit = true;
//        return MANAGE_PRODUCT_VIEW_NAME;
//    }

//    public String editProduct() {
//        LOGGER.info("Editing productModel : " + productModel);
//        for (ProductModel productModel : productModels) {
//            Long productId = productModel.getId();
//            if (Objects.equals(productId, this.productModel.getId())) {
//                LOGGER.info("Found productModel : " + productModel);
////                this.productModel = productModel;
//                productModel.setName(this.productModel.getName());
//                productModel.setStock(this.productModel.getStock());
//            }
//        }
////        productModels.add(productModel);
//        return "productModels";
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductModel> getProductModels() {
        return productModels;
    }

    public void setProductModels(List<ProductModel> productModels) {
        this.productModels = productModels;
    }
}

