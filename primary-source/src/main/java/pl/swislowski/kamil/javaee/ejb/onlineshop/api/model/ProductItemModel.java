package pl.swislowski.kamil.javaee.ejb.onlineshop.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductItemModel implements Serializable {

    private int amount = 1;

    private ProductModel product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductItemModel that = (ProductItemModel) o;
        return product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
