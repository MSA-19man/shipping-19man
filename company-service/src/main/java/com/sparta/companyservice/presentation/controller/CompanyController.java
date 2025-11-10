package com.sparta.companyservice.presentation.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.common.response.ApiResponse;
import com.sparta.common.response.PageResponse;
import com.sparta.companyservice.application.dto.CreateCompanyCommand;
import com.sparta.companyservice.application.dto.FindCompanyResult;
import com.sparta.companyservice.application.dto.GetCompanyResult;
import com.sparta.companyservice.application.dto.PageCommand;
import com.sparta.companyservice.application.service.CompanyService;
import com.sparta.companyservice.domain.model.Company;
import com.sparta.companyservice.presentation.request.CreateCompanyRequest;
import com.sparta.companyservice.presentation.response.CreateCompanyResponse;
import com.sparta.companyservice.presentation.response.FindCompanyResponse;
import com.sparta.companyservice.presentation.response.GetCompanyResponse;

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
	public ResponseEntity<ApiResponse<CreateCompanyResponse>> createCompany(
		@Valid @RequestBody CreateCompanyRequest request
	) {
		CreateCompanyCommand command = request.toCommand();

		Company company = companyService.createCompany(command);

		return ResponseEntity.status(HttpStatus.CREATED)
			.body(ApiResponse.success(CreateCompanyResponse.from(company),
				"업체 생성을 성공 했습니다."));
	}

	/**
	 * 업체 단건 조회 - 권한: DELIVERY_MANAGER, HUB_MANAGER, MASTER, SUPPLIER_MANAGER
	 */
	@GetMapping("/{companyId}")
	public ResponseEntity<ApiResponse<FindCompanyResponse>> getCompany(
		@PathVariable("companyId") UUID companyId
	) {
		FindCompanyResult result = companyService.getCompany(companyId);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ApiResponse.success(
				FindCompanyResponse.fromResult(result),
				"해당 업체 조회에 성공했습니다.")
			);
	}

	/**
	 * 업체 다건 조회 (페이징)
	 * 권한: DELIVERY_MANAGER, HUB_MANAGER, MASTER, SUPPLIER_MANAGER
	 */
	@GetMapping
	public ResponseEntity<ApiResponse<PageResponse<GetCompanyResponse>>> getCompanies(
		@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
		@RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
		@RequestParam(name = "sort", required = false, defaultValue = "createdAt") String sort,
		@RequestParam(name = "direction", required = false, defaultValue = "DESC") String direction
	) {
		PageCommand command = PageCommand.of(page, size, sort, direction);

		Page<GetCompanyResult> results = companyService.getCompanies(command.toPageable());

		Page<GetCompanyResponse> responsePage = results.map(GetCompanyResponse::fromResult);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ApiResponse.success(PageResponse.of(responsePage)));
	}
}
