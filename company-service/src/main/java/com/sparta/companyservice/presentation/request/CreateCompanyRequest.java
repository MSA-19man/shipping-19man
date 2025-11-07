package com.sparta.companyservice.presentation.request;

import java.util.UUID;

import com.sparta.companyservice.application.dto.CreateCompanyCommand;
import com.sparta.companyservice.domain.model.CompanyType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCompanyRequest(

	@NotBlank(message = "업체명은 필수입니다.")
	@Size(max = 100, message = "업체명은 100자 이하여야 합니다.")
	String name,

	@NotNull(message = "업체 타입은 필수입니다.")
	CompanyType type,

	@NotNull(message = "허브 ID는 필수입니다.")
	UUID hubId,

	@NotBlank(message = "주소는 필수입니다.")
	@Size(max = 500, message = "주소는 500자 이하여야 합니다.")
	String companyAddress

) {
	public CreateCompanyCommand toCommand() {
		return CreateCompanyCommand.builder()
			.name(name)
			.type(type)
			.hubId(hubId)
			.companyAddress(companyAddress)
			.build();
	}
}