package com.sparta.productservice.presentation.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sparta.productservice.domain.model.Product;

import lombok.Builder;

@Builder
public record CreateProductResponse(
	UUID productId,
	String name,
	UUID companyId,
	UUID hubId,
	Integer stock,
	LocalDateTime createdAt,
	Long createdBy,
	LocalDateTime updatedAt,
	Long updatedBy
) {
	public static CreateProductResponse from(Product product) {
		return CreateProductResponse.builder()
			.productId(product.getId())
			.name(product.getName())
			.companyId(product.getCompanyId())
			.hubId(product.getHubId())
			.stock(product.getStock())
			.createdAt(product.getCreatedAt())
			.createdBy(product.getCreatedBy())
			.updatedAt(product.getUpdatedAt())
			.updatedBy(product.getUpdatedBy())
			.build();
	}
}
