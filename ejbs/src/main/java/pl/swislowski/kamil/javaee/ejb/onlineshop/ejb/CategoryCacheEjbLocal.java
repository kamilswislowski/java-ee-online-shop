package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CategoryCacheEjbLocal {
    List<Category> categories();
}
