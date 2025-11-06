package com.sparta.companyservice.presentation.request;

import java.util.UUID;

import com.sparta.companyservice.domain.model.CompanyType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCompanyRequest {

	@NotBlank(message = "업체명은 필수입니다.")
	@Size(max = 100, message = "업체명은 100자 이하여야 합니다.")
	private String name;

	@NotNull(message = "업체 타입은 필수입니다.")
	private CompanyType type;

	@NotNull(message = "허브 ID는 필수입니다.")
	private UUID hubId;

	@NotBlank(message = "주소는 필수입니다.")
	@Size(max = 500, message = "주소는 500자 이하여야 합니다.")
	private String companyAddress;
}
