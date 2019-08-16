package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.entity.CategoryEntity;

import java.util.List;

public interface CategoryDao {

    CategoryEntity read(Long id);

    List<Category> categories();
//    throws ReadCategoryDaoException;
}
