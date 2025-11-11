package com.sparta.userservice.user.infra.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "company-service")
public interface CompanyClient {

	@GetMapping("/internal/v1/companies/{companyId}/exists")
	Boolean existByCompanyId(@PathVariable("companyId") UUID companyId);
}
