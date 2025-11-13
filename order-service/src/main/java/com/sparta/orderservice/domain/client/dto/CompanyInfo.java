package com.sparta.orderservice.domain.client.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record CompanyInfo(
	UUID companyId,
	String name,
	CompanyType type,
	UUID hubId,
	String address
) {
	public enum CompanyType {
		PRODUCER,  // 공급 업체
		RECEIVER   // 수령 업체
	}
}
