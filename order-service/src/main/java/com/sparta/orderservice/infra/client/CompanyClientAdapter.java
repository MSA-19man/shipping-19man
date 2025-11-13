package com.sparta.orderservice.infra.client;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sparta.orderservice.domain.client.CompanyClient;
import com.sparta.orderservice.domain.client.dto.CompanyInfo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CompanyClientAdapter implements CompanyClient {

	private final CompanyFeignClient companyFeignClient;

	@Override
	public CompanyInfo getCompany(UUID companyId) {
		return companyFeignClient.getCompany(companyId);
	}
}
