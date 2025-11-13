package com.sparta.productservice.presentation.request;

import java.util.UUID;

import com.sparta.productservice.application.dto.DeductStockCommand;

public record ReceiveStockRequest(
	Integer quantity
) {

	public DeductStockCommand toCommand(UUID productId) {
		return DeductStockCommand.builder()
			.productId(productId)
			.quantity(quantity)
			.build();
	}
}
