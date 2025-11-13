package com.sparta.orderservice.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
	PENDING("주문 대기"),
	CANCELED("주문 취소"),
	FAILED("주문 실패"),
	CONFIRMED("주문 확인");

	private final String description;
}