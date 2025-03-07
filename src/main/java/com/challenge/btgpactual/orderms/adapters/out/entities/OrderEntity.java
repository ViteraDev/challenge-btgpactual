package com.challenge.btgpactual.orderms.adapters.out.entities;

import com.challenge.btgpactual.orderms.domain.OrderItem;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.FileFilter;
import java.math.BigDecimal;
import java.util.List;

@Document(collection = "tb_orders")
public class OrderEntity {

    //#region Constructors
    public OrderEntity(){

    }
    public OrderEntity(Long orderId, Long customerId, BigDecimal total, List<OrderItem> items) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.total = total;
        this.items = items;
    }
    //#endregion

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
    @MongoId
    private Long orderId;

    @Indexed(name = "customer_id_index")
    private Long customerId;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal total;
    private List<OrderItem> items;

}
