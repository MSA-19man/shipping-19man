package com.sparta.companyservice.presentation.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sparta.companyservice.domain.model.Company;
import com.sparta.companyservice.domain.model.CompanyType;

import lombok.Builder;

@Builder
public record CreateCompanyResponse(

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
	public static CreateCompanyResponse from(Company company) {
		return CreateCompanyResponse.builder()
			.companyId(company.getId())
			.name(company.getName())
			.type(company.getType())
			.hubId(company.getHubId())
			.companyAddress(company.getCompanyAddress())
			.createdAt(company.getCreatedAt())
			.createdBy(company.getCreatedBy())
			.updatedAt(company.getUpdatedAt())
			.updatedBy(company.getUpdatedBy())
			.build();
	}
}

