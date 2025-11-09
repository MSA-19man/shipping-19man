package com.sparta.deliveryservice.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryStatus {

	HUB_WAITING("허브 대기", "출발 허브에서 대기 중"),
	HUB_MOVING("허브 이동 중", "허브 간 이동 중"),
	HUB_ARRIVED("허브 도착", "목적지 허브 도착"),
	COMPANY_MOVING("업체 배송 중", "수령 업체로 배송 중"),
	COMPLETED("배송 완료", "최종 배송 완료"),
	CANCELLED("배송 취소", "배송이 취소됨");

	private final String displayName;
	private final String description;
}
