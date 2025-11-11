package com.sparta.productservice.infra.client.dto;

import java.util.UUID;

public record CompanyResponse(
	UUID companyId,
	String name,
	String type,
	UUID hubId,
	String companyAddress
) {
}
