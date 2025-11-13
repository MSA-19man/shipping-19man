package com.sparta.orderservice.domain.client;

import java.util.UUID;

public interface DeliveryMessagePublisher {

	/**
	 * 배송 생성 메시지 발행
	 *
	 * @param orderId 주문 ID
	 * @param userId 사용자 ID
	 * @param departureHubId 출발 허브 ID
	 * @param arrivalHubId 도착 허브 ID
	 * @param deliveryAddress 배송 주소
	 * @param receiverCompany 수령 업체명
	 */
	void publishDeliveryCreation(
		UUID orderId,
		Long userId,
		UUID departureHubId,
		UUID arrivalHubId,
		String deliveryAddress,
		String receiverCompany
	);
}
