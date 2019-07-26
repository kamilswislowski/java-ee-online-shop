package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//@Named("defaultCategoryDao")
@QualifierDefaultCategoryDao
public class DefaultCategoryDao implements CategoryDao {

    @Resource(lookup = "jdbc/PostgresDataSource")
    private DataSource dataSource;

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
}
