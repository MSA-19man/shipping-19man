package com.sparta.orderservice.application.dto;

import java.util.UUID;

import com.sparta.orderservice.domain.model.Order;

import lombok.Builder;

@Builder
public record CreateOrderCommand(
	UUID supplyCompanyId,
	UUID receiveCompanyId,
	Long userId,
	UUID productId,
	Integer quantity,
	String message
) {
	public Order toEntity(Long userId) {
		return Order.of(
			supplyCompanyId,
			receiveCompanyId,
			userId,
			productId,
			quantity,
			message
		);
	}
}
