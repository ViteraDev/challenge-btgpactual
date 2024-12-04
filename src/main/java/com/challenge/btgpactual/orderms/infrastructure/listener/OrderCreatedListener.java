package com.challenge.btgpactual.orderms.infrastructure.listener;


import com.challenge.btgpactual.orderms.application.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.challenge.btgpactual.orderms.infrastructure.listener.dto.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


import static com.challenge.btgpactual.orderms.infrastructure.config.RabbitMqConfig.ORDER_CREATED_QUEUE;

@Component
public class OrderCreatedListener {

    private final Logger log = LoggerFactory.getLogger(OrderCreatedListener.class);
    private final OrderService orderService;

    public OrderCreatedListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message){
        log.info("Message consumed {}",message);
        orderService.save(message.getPayload());
    }
}
