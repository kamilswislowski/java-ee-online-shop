package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import javax.ejb.Remote;

@Remote
public interface CartEjbRemote {
    void add();

    void checkout();
}
