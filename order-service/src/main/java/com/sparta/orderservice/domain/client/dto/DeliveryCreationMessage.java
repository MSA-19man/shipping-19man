package com.sparta.orderservice.domain.client.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record DeliveryCreationMessage(
	UUID orderId,
	Long userId,
	UUID departureHubId,
	UUID arrivalHubId,
	String deliveryAddress,
	String receiverCompany
) {
}
