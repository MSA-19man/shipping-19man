package com.sparta.companyservice.presentation.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.companyservice.application.dto.FindCompanyResult;
import com.sparta.companyservice.application.service.CompanyService;
import com.sparta.companyservice.presentation.response.InternalCompanyResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/internal")
@RequiredArgsConstructor
public class CompanyInternalController {

	private final CompanyService companyService;

	@GetMapping("/v1/companies/{companyId}")
	public InternalCompanyResponse getCompany(
		@PathVariable("companyId") UUID companyId
	) {
		FindCompanyResult company = companyService.getCompany(companyId);

		return InternalCompanyResponse.fromResult(company);
	}
}
