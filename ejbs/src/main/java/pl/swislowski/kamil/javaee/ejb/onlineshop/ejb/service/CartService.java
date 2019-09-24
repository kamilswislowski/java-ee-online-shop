package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductItemModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.ProductEjbLocal;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.mapper.ProductItemMapper;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CartService {
    @Inject
    private ProductEjbLocal productEjbLocal;

    @Inject
    private ProductItemMapper productItemMapper;

    public ProductItemModel updateProductItemAmount(Long id, boolean addToCart) {
        ProductModel productModel = productEjbLocal.read(id);
        if (addToCart) {
            productModel.setStock(productModel.getStock() - 1);
        } else {
            productModel.setStock(productModel.getStock() + 1);
        }
        ProductModel updatedProductModel = productEjbLocal.update(productModel);
        return productItemMapper.toProductItem(updatedProductModel);
    }
}
