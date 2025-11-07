package com.sparta.companyservice.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.companyservice.application.dto.CreateCompanyCommand;
import com.sparta.companyservice.application.service.CompanyService;
import com.sparta.companyservice.domain.model.Company;
import com.sparta.companyservice.presentation.request.CreateCompanyRequest;
import com.sparta.companyservice.presentation.response.CompanyResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/companies")
public class CompanyController {

	private final CompanyService companyService;

	/**
	 * 업체 생성 - 권한: HUB_MANAGER, MASTER
	 */
	@PostMapping
	public ResponseEntity<CompanyResponse> createCompany(
		// TODO: @CurrentUser
		@Valid @RequestBody CreateCompanyRequest request
	) {
		CreateCompanyCommand command = CreateCompanyCommand.builder()
			.name(request.getName())
			.type(request.getType())
			.hubId(request.getHubId())
			.companyAddress(request.getCompanyAddress())
			.build();

		Company company = companyService.createCompany(command);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(CompanyResponse.from(company));
	}
}