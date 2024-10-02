package com.RESTful.Api.Dto;

public record ProductRequest(Long productId,String name, String description, Double price) {
}
