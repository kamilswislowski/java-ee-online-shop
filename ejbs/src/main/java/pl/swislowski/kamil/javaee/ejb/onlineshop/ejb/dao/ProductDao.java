package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.entity.ProductEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

public class ProductDao {

    private static final Logger LOGGER = Logger.getLogger(ProductDao.class.getName());

    @PersistenceContext(unitName = "OnlineShopPU")
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<ProductEntity> list() {
//        Query query = entityManager.createQuery("SELECT e FROM ProductEntity e");
//        List resultList = query.getResultList();
        Query namedQuery = entityManager.createNamedQuery("ProductEntity.getAll");
        List resultList = namedQuery.getResultList();
        LOGGER.info("List of products : " + resultList);
        return resultList;
    }

    public ProductEntity create(ProductEntity productEntity) {
        LOGGER.info("Creating ProductEntity ...");
        entityManager.persist(productEntity);
        LOGGER.info("Created ProductEntity : " + productEntity);
        return productEntity;
    }

    public ProductEntity read(Long id){
        return entityManager.find(ProductEntity.class, id);
    }

    public void delete(Long id) {
        ProductEntity entity = entityManager.find(ProductEntity.class, id);
        entityManager.remove(entity);
    }

    public ProductEntity update(ProductEntity productEntity) {
        return entityManager.merge(productEntity);
    }
}
