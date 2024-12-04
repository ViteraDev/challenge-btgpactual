package com.challenge.btgpactual.orderms.domain;

import java.math.BigDecimal;
import java.util.List;

public class Order {

    //#region Getter and Setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
    //#endregion
    private Long orderId;
    private Long customerId;
    private BigDecimal total;
    private List<OrderItem> items;
}
