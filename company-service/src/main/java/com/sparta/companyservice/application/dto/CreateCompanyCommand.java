package com.sparta.companyservice.application.dto;

import java.util.UUID;

import com.sparta.companyservice.domain.model.CompanyType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateCompanyCommand {
	private final String name;
	private final CompanyType type;
	private final UUID hubId;
	private final String companyAddress;
}
