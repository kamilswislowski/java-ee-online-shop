package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductItemModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;

import javax.inject.Named;

@Named
public class ProductItemMapper {

    public ProductItemModel toProductItem(ProductModel productModel) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<ProductModel, ProductItemModel> typeMap = modelMapper.typeMap(ProductModel.class, ProductItemModel.class);
        typeMap.addMapping(ProductModel::getStock, ProductItemModel::setAmount);
        return modelMapper.map(productModel, ProductItemModel.class);
    }

    /*
    modelMapper.addMappings(mapper -> {
  mapper.map(src -> src.getBillingAddress().getStreet(),
      Destination::setBillingStreet);
  mapper.map(src -> src.getBillingAddress().getCity(),
      Destination::setBillingCity);
});
     */
}
