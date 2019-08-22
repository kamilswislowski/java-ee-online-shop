package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Order;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.entity.CategoryEntity;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.entity.ProductEntity;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.CategoryService;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.ProductService;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

@Stateful
public class CartEjb implements CartEjbRemote {
    private static final Logger LOGGER = Logger.getLogger(CartEjb.class.getName());

    @Inject
    private CategoryCacheEjbLocal categoryCacheEjbLocal;

    @Inject
    private CategoryService categoryService;

    @Inject
    private ProductService productService;

    public CartEjb() {
    }

    @PostConstruct
    public void initialize(){
        LOGGER.info("Initializing...");
        List<Category> categories = categoryCacheEjbLocal.categories();
        LOGGER.info("Kategorie : " + categories);
    }

    public void add() {
        LOGGER.info("Adding product ...");
        CategoryEntity categoryEntity = categoryService.read(2L);
//        CategoryEntity categoryEntity = categoryService.create(new CategoryEntity("Tekstylia"));
        LOGGER.info("Created CategoryEntity : " + categoryEntity);
        LOGGER.info("All Products for CategoryEntity : " + categoryEntity + " = " + categoryEntity.getProducts());

        ProductEntity productEntity = new ProductEntity("Banany", BigDecimal.valueOf(9.99), 30);
        productEntity.setCategory(categoryEntity);
//        productEntity.setCategory(new CategoryEntity("Owoce"));
        productService.create(productEntity);
        LOGGER.info("Added ProductEntity : " + productEntity);
    }

    public void checkout(Order order) {
        LOGGER.info("Checking out order " + order);
    }
}
