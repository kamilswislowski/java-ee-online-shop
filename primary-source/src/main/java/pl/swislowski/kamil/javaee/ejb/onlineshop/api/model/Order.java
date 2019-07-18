package pl.swislowski.kamil.javaee.ejb.onlineshop.api.model;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable{
    private List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderItems=" + orderItems +
                '}';
    }
}

