package com.sparta.orderservice.domain.client.dto;

import lombok.Builder;

@Builder
public record DeductStockRequest(
	Integer quantity
) {
}
