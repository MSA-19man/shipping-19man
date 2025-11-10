package com.sparta.productservice.infra.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sparta.productservice.infra.client.dto.CompanyResponse;

@FeignClient(name = "company-service")
public interface CompanyClient {

	@GetMapping("/internal/v1/companies/{companyId}")
	CompanyResponse getCompany(@PathVariable("companyId") UUID companyId);
}