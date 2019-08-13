package pl.swislowski.kamil.javaee.ejb.onlineshop.web.jsf.beans;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Order;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.CartEjbRemote;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.CategoryCacheEjbLocal;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;

//@RequestScoped
//@Named
@ManagedBean(name = "cartJsfBean")
public class CartJsfBean implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(CartJsfBean.class.getName());

    @EJB
    private CartEjbRemote cartEjbRemote;

    @EJB
    private CategoryCacheEjbLocal categoryCacheEjbLocal;

    private String name;

    public CartJsfBean() {
    }

    public String checkout() {
        LOGGER.info("Checking out...");
        cartEjbRemote.checkout(new Order(new ArrayList<>()));
        categoryCacheEjbLocal.categories();

//        return "hello";
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

