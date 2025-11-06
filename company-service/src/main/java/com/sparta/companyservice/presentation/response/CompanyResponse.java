package com.sparta.companyservice.presentation.response;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sparta.companyservice.domain.model.Company;
import com.sparta.companyservice.domain.model.CompanyType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyResponse {

	private UUID companyId;
	private String name;
	private CompanyType type;
	private UUID hubId;
	private String companyAddress;
	private LocalDateTime createdAt;
	private Long createdBy;
	private LocalDateTime updatedAt;
	private Long updatedBy;

	public static CompanyResponse from(Company company) {
		return CompanyResponse.builder()
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
