package com.challenge.btgpactual.orderms.adapters.in.controller.dto.order;

import com.challenge.btgpactual.orderms.adapters.out.entities.OrderEntity;

import java.math.BigDecimal;

public record OrderResponse(
        Long orderId,
        Long costumerId,
        BigDecimal total) {

    public static OrderResponse toResponse(OrderEntity entity){
        return new OrderResponse(entity.getOrderId(),entity.getCustomerId(),entity.getTotal());
    }
}
