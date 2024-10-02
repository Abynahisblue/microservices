package com.order.service.services;

import com.order.service.Dto.OrderRequest;
import com.order.service.Dto.OrderResponse;
import com.order.service.exception.NotFoundException;
import com.order.service.grpc.ProductRequest;
import com.order.service.grpc.ProductResponse;
import com.order.service.grpc.ProductServiceGrpc;
import com.order.service.model.Order;
import com.order.service.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderServiceImp{

    private final OrderRepository orderRepository;
    private final ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

    public OrderService(OrderRepository orderRepository, ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub) {
        this.orderRepository = orderRepository;
        this.productServiceBlockingStub = productServiceBlockingStub;
    }
    public OrderResponse getProduct(OrderRequest orderRequest) {
        ProductRequest productRequest = ProductRequest.newBuilder()
                .setOrderId(orderRequest.productId())
                .build();

        ProductResponse productResponse = productServiceBlockingStub.getProduct(productRequest);

        Order orderProduct = Order.builder()
                .productId(productResponse.getId())
                .totalPrice(productResponse.getPrice())
                .customerName(orderRequest.customerName())
                .status(orderRequest.status())
                .quantity(orderRequest.quantity())
                .totalPrice(productResponse.getPrice() * orderRequest.quantity())
                .build();

        orderRepository.save(orderProduct);
        return new OrderResponse(
                orderProduct.getId(),
                orderProduct.getProductId(),
                orderProduct.getCustomerName(),
                orderProduct.getStatus(),
                orderProduct.getQuantity(),
                orderProduct.getTotalPrice()
        );
    }

    @Override
    public OrderResponse getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Order with id " + orderId + " not found"));

        return OrderResponse.builder()
                .id(order.getId())
                .totalPrice(order.getTotalPrice())
                .customerName(order.getCustomerName())
                .quantity(order.getQuantity())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    @Override
    public List<OrderResponse> getOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> OrderResponse.builder()
                        .id(order.getId())
                        .totalPrice(order.getTotalPrice())
                        .customerName(order.getCustomerName())
                        .quantity(order.getQuantity())
                        .status(order.getStatus())
                        .totalPrice(order.getTotalPrice())
                        .build())
                .collect(Collectors.toList());
    }
}
