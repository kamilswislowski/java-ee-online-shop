package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;

import javax.inject.Named;
import java.util.List;

//@Named("vipCategoryDao")
public class VipCategoryDao implements CategoryDao {
    @Override
    public List<Category> categories() {
        return null;
    }
}
