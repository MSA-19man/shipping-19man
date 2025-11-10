package com.sparta.companyservice.presentation.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sparta.companyservice.application.dto.GetCompanyResult;
import com.sparta.companyservice.domain.model.CompanyType;

import lombok.Builder;

@Builder
public record GetCompanyResponse(
	UUID companyId,
	String name,
	CompanyType type,
	UUID hubId,
	String companyAddress,
	LocalDateTime createdAt,
	Long createdBy,
	LocalDateTime updatedAt,
	Long updatedBy
) {
	public static GetCompanyResponse fromResult(GetCompanyResult result) {
		return GetCompanyResponse.builder()
			.companyId(result.id())
			.name(result.name())
			.type(result.type())
			.hubId(result.hubId())
			.companyAddress(result.companyAddress())
			.createdAt(result.createdAt())
			.createdBy(result.createdBy())
			.updatedAt(result.updatedAt())
			.updatedBy(result.updatedBy())
			.build();
	}
}
