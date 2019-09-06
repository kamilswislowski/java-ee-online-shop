package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.entity.CategoryEntity;

import java.util.List;

public interface CategoryDao {

    List<CategoryEntity> list();

    CategoryEntity read(Long id);

    List<Category> categories();

    CategoryEntity create(CategoryEntity categoryEntity);
//    throws ReadCategoryDaoException;
}
