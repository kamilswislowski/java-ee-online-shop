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

    //TODO: Zamienić listę modeli na ProductItemy.
    //TODO: Stworzyć CartService w którym umieścić logikę updateowania stacka dla produktów.
    //TODO: W CartBean wywołać metody z CartService.
    //TODO: Wychodzimy z założenia, że metoda checkOut modyfikuje ilość dostępnych produktów.
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
        cartEjbRemote.checkout(productModels.get(0));
        categoryCacheEjbLocal.categories();

//        return "hello";
        return null;
    }

    public List<ProductModel> getProductModels() {
        return productModels;
    }

    public void setProductModels(List<ProductModel> productModels) {
        this.productModels = productModels;
    }

    public void readQueue() {
        cartEjbRemote.readQueue();
    }
}

