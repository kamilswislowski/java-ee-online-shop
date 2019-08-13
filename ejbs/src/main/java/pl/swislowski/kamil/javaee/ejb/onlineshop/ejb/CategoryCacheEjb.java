package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.CategoryService;

import javax.annotation.PostConstruct;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Singleton
@Startup
public class CategoryCacheEjb implements CategoryCacheEjbLocal {
    private static final Logger LOGGER = Logger.getLogger(CategoryCacheEjb.class.getName());
    private List<Category> categories = new ArrayList<>();

    @Inject
    private CategoryService categoryService;

    public CategoryCacheEjb() {
    }

    @PostConstruct
    public void initialize() {
        LOGGER.info("Initializing...");
        categories.add(new Category(1L, "Tea", null));
        categories.add(new Category(2L, "Coffee", null));
        categories.add(new Category(3L, "Meat", null));
    }

    @Schedule(minute = "*/1", hour = "*")
    public void refreshCache() {
        LOGGER.info("Refreshing cache ...");
        categories = categoryService.categories();
        LOGGER.info("" + categories);
    }

    @Override
    public List<Category> categories() {
        LOGGER.info("" + categories);
        return categories;
    }
}
