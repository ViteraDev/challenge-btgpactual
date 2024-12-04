package com.challenge.btgpactual.orderms.infrastructure.listener.dto;

import java.math.BigDecimal;

public record OrderItemCreatedEvent(
        String produto,
        Integer quantidade,
        BigDecimal preco
) {
}
