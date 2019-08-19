package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao;

import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.entity.ProductEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

public class ProductDao {

    private static final Logger LOGGER = Logger.getLogger(ProductDao.class.getName());

    @PersistenceContext(unitName = "OnlineShopPU")
    private EntityManager entityManager;

    public ProductEntity create(ProductEntity productEntity) {
        LOGGER.info("Creating ProductEntity ...");
        entityManager.persist(productEntity);
        LOGGER.info("Created ProductEntity : " + productEntity);
        return productEntity;
    }

    public void delete(Long id) {
        ProductEntity entity = entityManager.find(ProductEntity.class, id);
        entityManager.remove(entity);
    }
}
