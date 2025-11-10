package com.sparta.companyservice.application.dto;

import java.util.UUID;

import com.sparta.companyservice.domain.model.CompanyType;

import lombok.Builder;

@Builder
public record CreateCompanyCommand(
	String name,
	CompanyType type,
	UUID hubId,
	String companyAddress
) {
}