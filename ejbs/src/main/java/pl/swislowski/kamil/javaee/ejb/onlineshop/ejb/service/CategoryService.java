package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.CategoryDao;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.QualifierDefaultCategoryDao;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    @Inject
    //@Named("defaultCategoryDao")
    @QualifierDefaultCategoryDao
    private CategoryDao categoryDao;
    
    public List<Category> categories() {
        return categoryDao.categories();
    }
}
