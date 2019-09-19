package pl.swislowski.kamil.javaee.ejb.onlineshop.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
public class ProductModel implements Serializable {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private Category category;

    public ProductModel(String name, BigDecimal price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
