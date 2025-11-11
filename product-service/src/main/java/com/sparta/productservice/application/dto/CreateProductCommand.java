package com.sparta.productservice.application.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record CreateProductCommand(
	String name,
	UUID companyId,
	UUID hubId,
	Integer stock
) {
}
