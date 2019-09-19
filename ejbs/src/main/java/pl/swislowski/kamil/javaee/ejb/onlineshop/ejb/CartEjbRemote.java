package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;

import javax.ejb.Remote;

@Remote
public interface CartEjbRemote {
    void add();

    void checkout(ProductModel productModel);

    void readQueue();
}
