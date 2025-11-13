package com.sparta.orderservice.domain.client.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record ProductStockInfo(
	UUID productId,
	String name,
	UUID hubId,
	Integer stock
) {
}
