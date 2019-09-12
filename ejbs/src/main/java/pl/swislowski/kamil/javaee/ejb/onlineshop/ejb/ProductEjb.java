package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.ProductModel;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.service.ProductService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Stateless
public class ProductEjb implements ProductEjbLocal {
    private static final Logger LOGGER = Logger.getLogger(ProductEjb.class.getName());

    @Inject
    private ProductService productService;

    private AtomicLong productCounter = new AtomicLong(1);

    @Override
    public void create(ProductModel productModel) {
        LOGGER.info("Creating productModel : " + productModel);
        ProductModel productModelFromService = productService.create(productModel);
        LOGGER.info("Created productModel : " + productModelFromService);
    }

    @Override
    public ProductModel read(Long id) {
        return productService.read(id);
    }

    @Override
    public ProductModel update(ProductModel productModel) {
        return productService.update(productModel);
    }

    @Override
    public void delete(Long id) {
        productService.delete(id);
    }

    @Override
    public List<ProductModel> list() {
        return productService.list();
    }
}
