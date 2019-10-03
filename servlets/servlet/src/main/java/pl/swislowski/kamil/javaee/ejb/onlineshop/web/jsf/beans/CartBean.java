package pl.swislowski.kamil.javaee.ejb.onlineshop.web.jsf.beans;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductItemModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.CartEjbRemote;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.CategoryCacheEjbLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
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

    private Set<ProductItemModel> products = new HashSet<>();

    @PostConstruct
    public void initialize() {
        LOGGER.info("Initializing ...");
    }

    public String addProductToCart(Long id) {
        ProductItemModel updateProductItemAmount = cartEjbRemote.updateProductItemAmount(id, true);

        if (products != null && products.size() <= 0) {
            products.add(updateProductItemAmount);
        } else {
            Iterator<ProductItemModel> iterator = products.iterator();
            while (iterator.hasNext()) {
                ProductItemModel productItemModel = iterator.next();
//            }
//            for (ProductItemModel productItemModel : products) {                    // Powyżej iteracja po liście przy użyciu iteratora. Warunek hasNext() sprawdza czy jest więcej elementów. Instrukcja next() odpowiedzialna jest za pobranie elementu z listy tak jak uchwyt productItemModel w for each.
                ProductModel productModel = productItemModel.getProduct();
                Long productModelId = productModel.getId();
                if (Objects.equals(productModelId, id)) {
                    productItemModel.setAmount(productItemModel.getAmount() + 1);
                }
            }
        }
        products.add(updateProductItemAmount);
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
        cartEjbRemote.checkout(new ProductModel("Kluczyki", BigDecimal.valueOf(199.89), 10));
        categoryCacheEjbLocal.categories();

//        return "hello";
        return null;
    }

    public Set<ProductItemModel> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductItemModel> products) {
        this.products = products;
    }

    public void readQueue() {
        cartEjbRemote.readQueue();
    }
}

