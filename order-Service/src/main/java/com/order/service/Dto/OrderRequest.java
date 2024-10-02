package com.order.service.Dto;

public record OrderRequest(Long productId, String customerName, Integer quantity, String status) {
}
