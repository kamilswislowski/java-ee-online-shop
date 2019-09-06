package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Order;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.entity.CategoryEntity;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.entity.ProductEntity;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.CategoryService;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.ProductService;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Stateful
public class CartEjb implements CartEjbRemote { // Wywoływane z klienta (oddzielny projekt w intellij), ponieważ ejb jest @Remote
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
    public void initialize() {
        LOGGER.info("Initializing...");
        List<Category> categories = categoryCacheEjbLocal.categories();
        LOGGER.info("Kategorie : " + categories);
    }

    public void add() { // Wywoływane z klienta (oddzielny projekt w intellij)
//        // #### Testowanie relacji @OneToMany oraz @OneToOne
//        LOGGER.info("Adding productModel ...");
//        CategoryEntity categoryEntity = categoryService.read(2L);
////        CategoryEntity categoryEntity = categoryService.create(new CategoryEntity("Tekstylia"));
//        LOGGER.info("Created CategoryEntity : " + categoryEntity);
////        LOGGER.info("All Products for CategoryEntity : " + categoryEntity + " = " + categoryEntity.getProducts());
//
//        ProductEntity productEntity = new ProductEntity("Banany", BigDecimal.valueOf(9.99), 30);
////        productEntity.setCategory(categoryEntity);
////        productEntity.setCategory(new CategoryEntity("Owoce"));
//        productService.create(productEntity);
//        LOGGER.info("Added ProductEntity : " + productEntity);

        // #### Testowanie relacji @ManyToMany

        CategoryEntity categoryTekstylia = categoryService.create(new CategoryEntity("Tekstylia"));
        LOGGER.info("Created CategoryEntity : " + categoryTekstylia);
        CategoryEntity categoryZaslony = categoryService.create(new CategoryEntity("Zaslony"));
        LOGGER.info("Created CategoryEntity : " + categoryZaslony);
        CategoryEntity categoryDom = categoryService.create(new CategoryEntity("Dom"));
        LOGGER.info("Created CategoryEntity : " + categoryDom);

        ProductEntity productCzarneZaslony = new ProductEntity("Czarne zaslony", BigDecimal.valueOf(9.99), 30);
//        productService.create(productCzarneZaslony);
        Set<CategoryEntity> categories = new HashSet<>();
        categories.add(categoryTekstylia);
        categories.add(categoryZaslony);
        categories.add(categoryDom);
        productCzarneZaslony.setCategories(categories);
    }

    public void checkout(Order order) {
        LOGGER.info("Checking out order " + order);
        productService.list();
        categoryService.list();
    }
}
