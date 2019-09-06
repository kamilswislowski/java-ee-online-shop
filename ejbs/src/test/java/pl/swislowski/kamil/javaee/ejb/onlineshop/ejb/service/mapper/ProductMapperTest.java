package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.entity.ProductEntity;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;

import java.math.BigDecimal;

public class ProductMapperTest {

    @Test
    public void toEntity() {
        ProductModel productModel = new ProductModel("Koc", BigDecimal.valueOf(12.99), 44);
        ProductMapper productMapper = new ProductMapper();

        ProductEntity productEntity = productMapper.toEntity(productModel);
        Assertions.assertEquals(productModel.getName(), productEntity.getName(),"Names aren't equals.");
        Assertions.assertEquals(productModel.getPrice(), productEntity.getPrice(),"Prices aren't equals.");
//        assertThat(productModel).isEqualToComparingFieldByFieldRecursively(productEntity);
//        assertThat(productModel).usingRecursiveComparison().isEqualTo(productEntity);
    }

    @Test
    public void fromEntity(){
        ProductEntity productEntity = new ProductEntity("Tablet", BigDecimal.valueOf(1099.99), 20);
        ProductMapper productMapper = new ProductMapper();

        ProductModel productModel = productMapper.fromEntity(productEntity);
        Assertions.assertEquals(productEntity.getName(), productModel.getName(), "Names aren't equals.");
        Assertions.assertEquals(productEntity.getPrice(), productModel.getPrice(), "Prices aren't equals.");
    }

}