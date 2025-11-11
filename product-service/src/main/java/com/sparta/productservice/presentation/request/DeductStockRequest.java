package com.sparta.productservice.presentation.request;

import java.util.UUID;

import com.sparta.productservice.application.dto.DeductStockCommand;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DeductStockRequest(
	@NotNull(message = "차감할 재고의 양은 필수입니다.")
	@Min(value = 1, message = "차감할 재고의 양은 1개 이상이여야 합니다.")
	Integer quantity
) {
	public DeductStockCommand toCommand(UUID productId) {
		return DeductStockCommand.builder()
			.productId(productId)
			.quantity(quantity)
			.build();
	}
}
