package com.order.service.services;

import com.order.service.Dto.OrderRequest;
import com.order.service.Dto.OrderResponse;
import com.order.service.grpc.ProductResponse;

import java.util.List;

public interface OrderServiceImp {
    OrderResponse getProduct(OrderRequest orderRequest);
    OrderResponse getOrder(Long id);
    List<OrderResponse> getOrders();
}
