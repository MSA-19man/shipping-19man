package com.sparta.productservice.presentation.response;

import java.util.UUID;

import com.sparta.productservice.domain.model.Product;

import lombok.Builder;

@Builder
public record ProductResponse(
	UUID productId,
	String name,
	UUID hubId,
	Integer stock
) {
	public static ProductResponse from(Product product) {
		return ProductResponse.builder()
			.productId(product.getId())
			.name(product.getName())
			.hubId(product.getHubId())
			.stock(product.getStock())
			.build();
	}
}
