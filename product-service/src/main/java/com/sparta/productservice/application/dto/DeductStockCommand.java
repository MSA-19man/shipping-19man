package com.sparta.productservice.application.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record DeductStockCommand(
	UUID productId,
	int quantity
) {
}
