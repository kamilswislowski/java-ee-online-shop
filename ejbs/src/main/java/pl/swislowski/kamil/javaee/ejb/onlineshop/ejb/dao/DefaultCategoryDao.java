package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;
import pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.entity.CategoryEntity;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//@Named("defaultCategoryDao")
@QualifierDefaultCategoryDao
public class DefaultCategoryDao implements CategoryDao {

    private static final Logger LOGGER = Logger.getLogger(DefaultCategoryDao.class.getName());

    @Resource(lookup = "jdbc/PostgresDataSource")
    private DataSource dataSource;

    @PersistenceContext(unitName = "OnlineShopPU")
    private EntityManager entityManager;

    @Override
    public CategoryEntity read(Long id) {
        LOGGER.info("Reading CategoryEntity with id : " + id);
        CategoryEntity categoryEntity = entityManager.find(CategoryEntity.class, id);
        LOGGER.info("Found categoryEntity : " + categoryEntity);
        return categoryEntity;
    }

    @Override
    public List<Category> categories() {//throws ReadCategoryDaoException {
        List<Category> categories = new ArrayList<>();

        try {
            Connection connection = dataSource.getConnection();
//            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Kamil", "Kamil", "Kamil");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CATEGORIES");
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getLong("id"));
                category.setName(resultSet.getString("name"));

                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
//            throw new ReadCategoryDaoException("Unable to read categories.", e);
        }

        return categories;
    }

    @Override
    public CategoryEntity create(CategoryEntity categoryEntity) {
        LOGGER.info("Creating CategoryEntity ...");
        entityManager.persist(categoryEntity);
        LOGGER.info("Created CategoryEntity : " + categoryEntity);
        return categoryEntity;
    }
}
