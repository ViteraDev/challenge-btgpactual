package com.challenge.btgpactual.orderms.adapters.in.controller;

import com.challenge.btgpactual.orderms.adapters.in.controller.dto.base.ApiResponse;
import com.challenge.btgpactual.orderms.adapters.in.controller.dto.base.PaginationResponse;
import com.challenge.btgpactual.orderms.adapters.in.controller.dto.order.OrderResponse;
import com.challenge.btgpactual.orderms.application.service.OrderService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(path ="/customer/{customerId}/orders")
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
            @PathVariable("customerId") Long customerId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize

    ){
        var pageResponse = orderService.findAllByCostumerId(customerId, PageRequest.of(page,pageSize));
        var totalOnOrders = orderService.findTotalOnOrderByCustomerId(customerId);

        return ResponseEntity.ok(new ApiResponse<>(
                Map.of("totalOnOrders", totalOnOrders),
                pageResponse.getContent(),
                PaginationResponse.fromPage(pageResponse)
        ));
    }
}
