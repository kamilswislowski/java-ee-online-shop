package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;

import java.util.List;

public interface CategoryEjbLocal {

    void create(Category category);

    Category read(Long id);

    Category update(Category category);

    void delete(Long id);

    List<Category> list();

}
