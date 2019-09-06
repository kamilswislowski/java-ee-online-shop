package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductEjbLocal {
    void create(ProductModel productModel);

    ProductModel read(Long id);

    ProductModel update(ProductModel productModel);

    void delete(Long id);

    List<ProductModel> list();
}
