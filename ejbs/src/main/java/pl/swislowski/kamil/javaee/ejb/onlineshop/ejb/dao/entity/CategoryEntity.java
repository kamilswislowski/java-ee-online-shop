package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY_ENTITY")
public class CategoryEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
