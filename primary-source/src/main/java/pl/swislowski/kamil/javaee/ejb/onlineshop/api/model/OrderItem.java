package pl.swislowski.kamil.javaee.ejb.onlineshop.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Product product;
    private Integer quantity;
}
