package com.challenge.btgpactual.orderms.application.service;


import com.challenge.btgpactual.orderms.adapters.in.controller.dto.order.OrderResponse;
import com.challenge.btgpactual.orderms.adapters.out.entities.OrderEntity;
import com.challenge.btgpactual.orderms.adapters.out.repositories.OrderRepository;
import com.challenge.btgpactual.orderms.infrastructure.listener.dto.OrderCreatedEvent;
import com.challenge.btgpactual.orderms.utils.mappers.OrderCreatedEventMapper;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    private final OrderCreatedEventMapper mapper;

    private final MongoTemplate mongoTemplate;
    public OrderService(OrderCreatedEventMapper mapper,OrderRepository orderRepository,MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mapper =  mapper;
        this.mongoTemplate = mongoTemplate;
    }

    public void save(OrderCreatedEvent event) {

        OrderEntity entity = this.mapper.toEntity(event);
        entity.setTotal(event.getTotal());
        entity.setItems(event
                .itens()
                .stream()
                .map(this.mapper::toItemEntity)
                .toList());


        orderRepository.save(entity);
    }

    public Page<OrderResponse> findAllByCostumerId(Long costumerId, PageRequest pageRequest){
       return orderRepository
               .findAllByCustomerId(costumerId,pageRequest)
               .map(OrderResponse::toResponse);
    }

    public BigDecimal findTotalOnOrderByCustomerId(Long customerId){
        var aggregations = newAggregation(
            match(Criteria.where("customerId").is(customerId)),
            group().sum("total").as("total")
        );

        var response = mongoTemplate.aggregate(aggregations,"tb_orders", Document.class);

        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }
}
