package com.sparta.companyservice.presentation.response;

import java.util.UUID;

import com.sparta.companyservice.application.dto.FindCompanyResult;
import com.sparta.companyservice.domain.model.CompanyType;

import lombok.Builder;

@Builder
public record InternalCompanyResponse(
	UUID companyId,
	String name,
	CompanyType type,
	UUID hubId,
	String companyAddress
) {
	public static InternalCompanyResponse fromResult(FindCompanyResult result) {
		return InternalCompanyResponse.builder()
			.companyId(result.id())
			.name(result.name())
			.type(result.type())
			.hubId(result.hubId())
			.companyAddress(result.companyAddress())
			.build();
	}
}
