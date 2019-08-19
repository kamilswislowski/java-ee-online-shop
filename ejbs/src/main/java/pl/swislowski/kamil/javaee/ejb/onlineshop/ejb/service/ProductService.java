package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service;

import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.ProductDao;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.entity.ProductEntity;

import javax.inject.Inject;

public class ProductService {

    @Inject
    private ProductDao productDao;

    public ProductEntity create(ProductEntity productEntity) {
        return productDao.create(productEntity);
    }

    public void delete(Long id) {
        productDao.delete(id);
    }
}
