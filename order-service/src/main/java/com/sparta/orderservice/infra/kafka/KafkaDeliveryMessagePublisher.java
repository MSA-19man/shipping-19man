package com.sparta.orderservice.infra.kafka;

import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.sparta.orderservice.domain.client.DeliveryMessagePublisher;
import com.sparta.orderservice.domain.client.dto.DeliveryCreationMessage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaDeliveryMessagePublisher implements DeliveryMessagePublisher {

	private static final String TOPIC = "delivery.creation.requested";

	private final KafkaTemplate<String, DeliveryCreationMessage> kafkaTemplate;

	@Override
	public void publishDeliveryCreation(
		UUID orderId,
		Long userId,
		UUID departureHubId,
		UUID arrivalHubId,
		String deliveryAddress,
		String receiverCompany
	) {
		log.info("[Kafka Adapter] 배송 생성 메시지 발행 - orderId: {}", orderId);
		DeliveryCreationMessage message = DeliveryCreationMessage.builder()
			.orderId(orderId)
			.userId(userId)
			.departureHubId(departureHubId)
			.arrivalHubId(arrivalHubId)
			.deliveryAddress(deliveryAddress)
			.receiverCompany(receiverCompany)
			.build();

		kafkaTemplate.send(TOPIC, message);
	}
}
