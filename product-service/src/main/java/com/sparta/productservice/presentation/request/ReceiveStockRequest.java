package com.sparta.productservice.presentation.request;

import java.util.UUID;

import com.sparta.productservice.application.dto.AddStockCommand;

public record ReceiveStockRequest(
	Integer quantity
) {

	public AddStockCommand toCommand(UUID productId) {
		return AddStockCommand.builder()
			.productId(productId)
			.quantity(quantity)
			.build();
	}
}
