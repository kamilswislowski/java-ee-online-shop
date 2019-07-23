package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao;

import pl.swislowski.kamil.javaee.ejb.onlineshop.api.model.Category;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//@Named("defaultCategoryDao")
@QualifierDefaultCategoryDao
public class DefaultCategoryDao implements CategoryDao {

    public List<Category> categories() {
        List<Category> categories = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Kamil", "Kamil", "Kamil");
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
        }

        return categories;
    }
}
