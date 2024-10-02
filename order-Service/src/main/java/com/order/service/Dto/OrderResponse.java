package com.order.service.Dto;

import lombok.Builder;

@Builder
public record OrderResponse(
        Long id,
        Long productId,
        String customerName,
        String status,
        Integer quantity,
        Double totalPrice
) {
}
