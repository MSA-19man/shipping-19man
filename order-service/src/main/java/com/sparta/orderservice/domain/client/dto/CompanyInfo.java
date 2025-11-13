package com.sparta.orderservice.domain.client.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CompanyInfo(
	UUID companyId,
	String name,
	CompanyType type,
	UUID hubId,
	String companyAddress
) {
	public enum CompanyType {
		PRODUCER,  // 공급 업체
		RECEIVER   // 수령 업체
	}
}
