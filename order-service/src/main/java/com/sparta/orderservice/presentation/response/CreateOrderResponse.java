package com.sparta.orderservice.presentation.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sparta.orderservice.domain.model.Order;
import com.sparta.orderservice.domain.model.OrderStatus;

import lombok.Builder;

@Builder
public record CreateOrderResponse(
	UUID orderId,
	UUID supplyCompanyId,
	UUID receiveCompanyId,
	UUID productId,
	Integer quantity,
	OrderStatus status,
	String message,
	LocalDateTime createdAt,
	Long createdBy,
	LocalDateTime updatedAt,
	Long updatedBy
) {

	public static CreateOrderResponse from(Order order) {
		return CreateOrderResponse.builder()
			.orderId(order.getId())
			.supplyCompanyId(order.getSupplyCompanyId())
			.receiveCompanyId(order.getReceiveCompanyId())
			.productId(order.getProductId())
			.quantity(order.getQuantity())
			.status(order.getStatus())
			.message(order.getMessage())
			.createdAt(order.getCreatedAt())
			.createdBy(order.getCreatedBy())
			.updatedAt(order.getUpdatedAt())
			.updatedBy(order.getUpdatedBy())
			.build();
	}
}
