package com.sparta.deliveryservice.presentation.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryCreateRequest {

	private UUID orderId;
	private UUID departureHubId;
	private UUID arrivalHubId;
	private String deliveryAddress;
	private String receiverName;
	private String receiverSlackId;
	private UUID companyAgentId;
	
}
