package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Order;

import javax.ejb.Remote;

@Remote
public interface CartEjbRemote {
    void add();

    void checkout(Order order);
}
