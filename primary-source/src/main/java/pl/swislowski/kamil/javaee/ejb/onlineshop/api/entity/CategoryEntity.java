package pl.swislowski.kamil.javaee.ejb.onlineshop.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "CATEGORY_ENTITY")
@NamedQuery(name = "CategoryEntity.getAll", query = "SELECT e FROM CategoryEntity e")
public class CategoryEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

//    @OneToMany(mappedBy = "category")
//    private Set<ProductEntity> products;

    @ManyToMany(mappedBy = "categories")
    private Set<ProductEntity> products;

    public CategoryEntity() {
    }

    public CategoryEntity(String name) {
        this.name = name;
    }

//    public Set<ProductEntity> getProducts() {
//        return products;
//    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
