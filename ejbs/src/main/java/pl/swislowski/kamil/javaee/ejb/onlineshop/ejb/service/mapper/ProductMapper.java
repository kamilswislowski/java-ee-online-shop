package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.mapper;

import org.modelmapper.ModelMapper;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.entity.ProductEntity;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;

import javax.inject.Named;

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


}
