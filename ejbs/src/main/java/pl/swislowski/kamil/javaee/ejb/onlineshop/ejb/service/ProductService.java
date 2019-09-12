package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service;

import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.mapper.ProductMapper;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.ProductDao;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.entity.ProductEntity;

import javax.inject.Inject;
import java.util.List;

public class ProductService {

    @Inject
    private ProductDao productDao;

    @Inject
    private ProductMapper productMapper;

    public List<ProductModel> list() {
        return productMapper.fromEntityList(productDao.list());
    }

    public ProductModel create(ProductModel productModel) {
        ProductEntity productEntity = productMapper.toEntity(productModel);
        ProductEntity productEntityFromDao = productDao.create(productEntity);
        return productMapper.fromEntity(productEntityFromDao);
    }

    public ProductModel read(Long id) {
        ProductEntity productEntity = productDao.read(id);
        return productMapper.fromEntity(productEntity);
    }

    public ProductModel update(ProductModel productModel) {
        ProductEntity productEntity = productMapper.toEntity(productModel);
        return productMapper.fromEntity(productDao.update(productEntity));
    }

    public void delete(Long id) {
        productDao.delete(id);
    }
}
