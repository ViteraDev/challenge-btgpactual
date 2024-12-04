package com.challenge.btgpactual.orderms.infrastructure.listener.dto;

import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;

import java.math.BigDecimal;
import java.util.List;

public record OrderCreatedEvent(
        Long codigoPedido,
        Long codigoCliente,
        List<OrderItemCreatedEvent> itens
        ) {
        public BigDecimal getTotal(){
                return this.itens
                        .stream()
                        .map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade())))
                        .reduce(BigDecimal::add)
                        .orElse(BigDecimal.ZERO);        }
}
