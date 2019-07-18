package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductEjbLocal {
    void create(Product product);

    Product read(Long id);

    Product update(Product product);

    void delete(Long id);

    List<Product> list();
}
