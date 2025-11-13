package com.sparta.orderservice.presentation.request;

import java.util.UUID;

import com.sparta.orderservice.application.dto.CreateOrderCommand;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(
	@NotNull(message = "공급 업체 ID는 필수입니다.")
	UUID supplyCompanyId,

	@NotNull(message = "수령 업체 ID는 필수입니다.")
	UUID receiveCompanyId,

	@NotNull(message = "상품 ID는 필수입니다.")
	UUID productId,

	@NotNull(message = "수량은 필수입니다.")
	@Min(value = 1, message = "수량은 1 이상이어야 합니다.")
	Integer quantity,

	String message
) {

	public CreateOrderCommand toCommand() {
		return CreateOrderCommand.builder()
			.supplyCompanyId(supplyCompanyId)
			.receiveCompanyId(receiveCompanyId)
			.productId(productId)
			.quantity(quantity)
			.message(message)
			.build();
	}
}
