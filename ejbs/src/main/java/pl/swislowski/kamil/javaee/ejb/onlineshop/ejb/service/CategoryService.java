package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.CategoryDao;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.QualifierDefaultCategoryDao;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.entity.CategoryEntity;

import javax.inject.Inject;
import java.util.List;

public class CategoryService {

    @Inject
    //@Named("defaultCategoryDao")
    @QualifierDefaultCategoryDao
    private CategoryDao categoryDao;

    public CategoryEntity read(Long id) {
        return categoryDao.read(id);
    }

    public List<Category> categories() {
        return categoryDao.categories();
    }
}
