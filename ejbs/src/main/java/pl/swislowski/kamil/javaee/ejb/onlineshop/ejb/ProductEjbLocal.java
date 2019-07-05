package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Product;

import java.util.List;

public interface ProductEjbLocal {
    void create(Product product);

    Product read(Long id);

    Product update(Product product);

    void delete(Long id);

    List<Product> list();
}
