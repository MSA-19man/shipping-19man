package com.sparta.orderservice.infra.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sparta.orderservice.domain.client.dto.CompanyInfo;

@FeignClient(name = "company-service")
public interface CompanyFeignClient {

	@GetMapping("/internal/v1/companies/{companyId}")
	CompanyInfo getCompany(@PathVariable("companyId") UUID companyId);

}
