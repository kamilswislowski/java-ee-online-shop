package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductItemModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;

import javax.inject.Named;
import java.util.logging.Logger;

@Named
public class ProductItemMapper {
    private static final Logger LOGGER = Logger.getLogger(ProductItemMapper.class.getName());

    public ProductItemModel toProductItem(ProductModel productModel) {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<ProductModel, ProductItemModel> typeMap = modelMapper.typeMap(ProductModel.class, ProductItemModel.class);
//        typeMap.addMapping(ProductModel::getStock, ProductItemModel::setAmount);
        ProductItemModel productItemModel = modelMapper.map(productModel, ProductItemModel.class);
        LOGGER.info("####################################################ProductItemModel : " + productItemModel);
        return productItemModel;
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
