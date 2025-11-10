package com.sparta.companyservice.presentation.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sparta.companyservice.application.dto.FindCompanyResult;
import com.sparta.companyservice.domain.model.CompanyType;

import lombok.Builder;

@Builder
public record FindCompanyResponse(

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

	public static FindCompanyResponse fromResult(FindCompanyResult result) {
		return FindCompanyResponse.builder()
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

