package com.sparta.deliveryservice.presentation.response;

import com.sparta.deliveryservice.domain.model.Delivery;
import com.sparta.deliveryservice.domain.model.DeliveryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class DeliveryCreateResponse {

	private String message;
	private UUID deliveryId;
	private UUID orderId;
	private DeliveryStatus status;
	private UUID departureHubId;
	private UUID arrivalHubId;
	private String deliveryAddress;
	private String receiverName;
	private String receiverSlackId;
	private UUID companyAgentId;

	public static DeliveryCreateResponse from(Delivery delivery) {
		return DeliveryCreateResponse.builder()
			.message("배송이 생성되었습니다.")
			.deliveryId(delivery.getId())
			.orderId(delivery.getOrderId())
			.status(delivery.getStatus())
			.departureHubId(delivery.getDepartureHubId())
			.arrivalHubId(delivery.getArrivalHubId())
			.deliveryAddress(delivery.getDeliveryAddress())
			.receiverName(delivery.getReceiverName())
			.receiverSlackId(delivery.getReceiverSlackId())
			.companyAgentId(delivery.getCompanyAgentId())
			.build();
	}
}
