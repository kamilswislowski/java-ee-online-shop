package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.mapper;

import org.modelmapper.ModelMapper;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.entity.ProductEntity;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class ProductMapper {

    public ProductEntity toEntity(ProductModel productModel) {
        ModelMapper modelMapper = new ModelMapper();
        ProductEntity productEntity = modelMapper.map(productModel, ProductEntity.class);
        return productEntity;
    }

    public ProductModel fromEntity(ProductEntity productEntity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(productEntity, ProductModel.class);
    }

    public List<ProductModel> fromEntityList(List<ProductEntity> productEntities) {
        List<ProductModel> productModels = new ArrayList<>();
        for (ProductEntity productEntity : productEntities) {
            ProductModel productModel = fromEntity(productEntity);
            productModels.add(productModel);
        }
        return productModels;
    }
}
