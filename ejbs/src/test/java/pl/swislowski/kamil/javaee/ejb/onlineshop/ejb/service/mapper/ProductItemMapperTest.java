package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductItemModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;

import java.math.BigDecimal;

class ProductItemMapperTest {

    @Test
    void toProductItem() {
        ProductModel productModel = new ProductModel("Myszka", BigDecimal.valueOf(13.99), 15);
        ProductItemMapper productItemMapper = new ProductItemMapper();

        ProductItemModel productItemModel = productItemMapper.toProductItem(productModel);
        System.out.println(productItemModel);
        Assertions.assertEquals(productModel.getStock(), productItemModel.getAmount());
    }
}