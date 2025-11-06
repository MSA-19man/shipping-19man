package com.sparta.companyservice.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CompanyType {
	PRODUCER("생산업체"),
	RECEIVER("수령업체");

	private final String description;
}
