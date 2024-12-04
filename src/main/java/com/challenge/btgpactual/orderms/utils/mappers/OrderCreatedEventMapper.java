package com.challenge.btgpactual.orderms.utils.mappers;

import com.challenge.btgpactual.orderms.adapters.out.entities.OrderEntity;
import com.challenge.btgpactual.orderms.domain.OrderItem;
import com.challenge.btgpactual.orderms.infrastructure.listener.dto.OrderCreatedEvent;
import com.challenge.btgpactual.orderms.infrastructure.listener.dto.OrderItemCreatedEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface OrderCreatedEventMapper {
    @Mapping(source = "codigoPedido", target = "orderId")
    @Mapping(source = "codigoCliente", target = "customerId")
    OrderEntity toEntity(OrderCreatedEvent orderCreatedEvent);

    @Mapping(source = "orderId", target = "codigoPedido")
    @Mapping(source = "customerId", target = "codigoCliente")
    @Mapping(source = "items", target = "itens")
    OrderCreatedEvent toDto(OrderEntity orderEntity);


    @Mapping(source = "produto", target = "product")
    @Mapping(source = "quantidade", target = "quantity")
    @Mapping(source = "preco", target = "price")
    OrderItem toItemEntity(OrderItemCreatedEvent orderItemCreatedEvent);


    @Mapping(source = "product", target = "produto")
    @Mapping(source = "quantity", target = "quantidade")
    @Mapping(source = "price", target = "preco")
    OrderItemCreatedEvent toItemDto(OrderItem orderItem);

}
