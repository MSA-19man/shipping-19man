package com.sparta.companyservice.application.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sparta.companyservice.domain.model.Company;
import com.sparta.companyservice.domain.model.CompanyType;

import lombok.Builder;

@Builder
public record FindCompanyResult(
	UUID id,
	String name,
	CompanyType type,
	UUID hubId,
	String companyAddress,
	LocalDateTime createdAt,
	Long createdBy,
	LocalDateTime updatedAt,
	Long updatedBy
) {
	public static FindCompanyResult from(Company company) {
		return FindCompanyResult.builder()
			.id(company.getId())
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
