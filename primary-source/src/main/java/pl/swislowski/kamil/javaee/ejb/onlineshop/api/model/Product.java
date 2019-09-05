package pl.swislowski.kamil.javaee.ejb.onlineshop.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
public class Product {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer stock;
    private Category category;
}
