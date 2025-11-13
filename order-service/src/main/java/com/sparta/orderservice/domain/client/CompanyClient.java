package com.sparta.orderservice.domain.client;

import java.util.UUID;

import com.sparta.orderservice.domain.client.dto.CompanyInfo;

public interface CompanyClient {
	CompanyInfo getCompany(UUID companyId);
}
