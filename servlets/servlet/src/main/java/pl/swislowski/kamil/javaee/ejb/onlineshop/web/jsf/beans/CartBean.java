package pl.swislowski.kamil.javaee.ejb.onlineshop.web.jsf.beans;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductItemModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.CartEjbRemote;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.CategoryCacheEjbLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//@RequestScoped
//@Named
@ManagedBean//(name = "cartJsfBean")
@SessionScoped
public class CartBean {
    private static final Logger LOGGER = Logger.getLogger(CartBean.class.getName());

    @EJB
    private CartEjbRemote cartEjbRemote;
    @EJB
    private CategoryCacheEjbLocal categoryCacheEjbLocal;

    private List<ProductItemModel> products = new ArrayList<>();


    @PostConstruct
    public void initialize() {
        LOGGER.info("Initializing ...");
    }

    public String addProductToCart(Long id) {
        LOGGER.info("" + id);
        ProductItemModel productItem = cartEjbRemote.updateProductItemAmount(id, true);
        products.add(productItem);
        return "cart";
    }

    public void delete(ProductItemModel productItemModel) {
        LOGGER.info("Deleting productItemModel ... with id: " + productItemModel);
        products.remove(productItemModel);
        ProductModel productModel = productItemModel.getProduct();
        if (productModel != null) {
            cartEjbRemote.updateProductItemAmount(productModel.getId(), false);
        }
    }

    public String checkout() {
        LOGGER.info("Checking out...");
//        cartEjbRemote.checkout(new Order(new ArrayList<>()));
//        cartEjbRemote.checkout(products.get(0));
        categoryCacheEjbLocal.categories();

//        return "hello";
        return null;
    }

    public List<ProductItemModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductItemModel> products) {
        this.products = products;
    }

    public void readQueue() {
        cartEjbRemote.readQueue();
    }
}

