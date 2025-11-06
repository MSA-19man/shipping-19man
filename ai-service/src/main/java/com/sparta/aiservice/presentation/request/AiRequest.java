package com.sparta.aiservice.presentation.request;

import java.util.List;
import java.util.UUID;

public record AiRequest(
	UUID orderId,                  // 주문 ID
	String productName,            // 상품명
	int quantity,                  // 수량
	String requestMessage,         // 요청사항
	String departureHub,           // 출발 허브
	List<String> viaHubs,          // 경유 허브 목록
	String arrivalAddress,         // 도착 주소
	String deliveryAgent,          // 배송 담당자 이름
	WorkingHours workingHours      // 근무 시간 (시작/종료)
) {
	public record WorkingHours(
		String start,              // 예: "09:00"
		String end                 // 예: "18:00"
	) {
	}
}
