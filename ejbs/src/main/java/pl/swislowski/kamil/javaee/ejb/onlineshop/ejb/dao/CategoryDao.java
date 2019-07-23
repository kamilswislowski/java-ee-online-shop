package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> categories();
}
