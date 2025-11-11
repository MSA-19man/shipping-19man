package com.sparta.productservice.presentation.response;

import java.util.UUID;

import com.sparta.productservice.domain.model.Product;

public record DeductStockResponse(
	UUID productId,
	String productName,
	Integer finalStock
) {
	public static DeductStockResponse from(Product product) {
		return new DeductStockResponse(
			product.getId(),
			product.getName(),
			product.getStock()
		);
	}
}
