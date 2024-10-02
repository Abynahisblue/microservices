package com.order.service.controller;

import com.order.service.Dto.OrderRequest;
import com.order.service.Dto.OrderResponse;
import com.order.service.grpc.ProductResponse;
import com.order.service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/create")
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        OrderResponse orderResponse = orderService.getProduct(orderRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable("id") Long id) {
        OrderResponse orderResponse = orderService.getOrder(id);
        return ResponseEntity.ok(orderResponse);
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }
}
