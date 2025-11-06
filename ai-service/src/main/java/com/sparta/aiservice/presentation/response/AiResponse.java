package com.sparta.aiservice.presentation.response;

import java.time.ZonedDateTime;
import java.util.UUID;

import com.sparta.aiservice.domain.model.Ai;

public record AiResponse(
	UUID aiId,                     // AI 데이터 ID
	UUID orderId,                  // 주문 ID
	ZonedDateTime finalDispatchDeadline, // 발송 마감 시점
	String aiSummary,              // 요약 문장
	ZonedDateTime createdAt        // 생성 시각 (UTC)
) {
	public static AiResponse from(Ai ai) {
		return new AiResponse(
			ai.getId(),
			ai.getOrderId(),
			ai.getFinalDispatchDeadline(),
			ai.getAiSummary(),
			ai.getCreatedAt().atZone(java.time.ZoneOffset.UTC)
		);
	}
}
