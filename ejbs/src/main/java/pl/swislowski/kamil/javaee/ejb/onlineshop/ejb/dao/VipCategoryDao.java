package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.entity.CategoryEntity;

import java.util.List;

//@Named("vipCategoryDao")
public class VipCategoryDao implements CategoryDao {
    @Override
    public CategoryEntity read(Long id) {
        return null;
    }

    @Override
    public List<Category> categories() {
        return null;
    }

    @Override
    public CategoryEntity create(CategoryEntity categoryEntity) {
        return null;
    }
}
