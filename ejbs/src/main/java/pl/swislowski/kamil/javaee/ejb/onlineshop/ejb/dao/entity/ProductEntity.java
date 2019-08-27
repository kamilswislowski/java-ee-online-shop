package pl.swislowski.kamil.javaee.ejb.onlineshop.ejb.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "PRODUCT_ENTITY")
@NamedQuery(name = "ProductEntity.getAll", query = "SELECT e FROM ProductEntity e")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    private Integer stock;

//    @ManyToOne
//    @JoinColumn(name = "CATEGORY_ID")
//    private CategoryEntity category;

//    @OneToOne
//    @JoinColumn(name = "CATEGORY_ID")
//    private CategoryEntity category;

    @ManyToMany
    @JoinTable(name = "PRODUCT_CATEGORY")
    private Set<CategoryEntity> categories;

    public ProductEntity() {
    }

    public ProductEntity(String name, BigDecimal price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
    }

    //    public CategoryEntity getCategory() {
//        return category;
//    }

//    public void setCategory(CategoryEntity category) {
//        this.category = category;
//    }

    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", categories=" + categories +
                '}';
    }
}
