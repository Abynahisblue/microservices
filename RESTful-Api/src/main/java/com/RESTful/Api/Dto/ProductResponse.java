package com.RESTful.Api.Dto;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Double price
) {
}
