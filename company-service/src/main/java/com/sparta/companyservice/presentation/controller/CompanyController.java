package com.sparta.companyservice.presentation.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
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
import com.sparta.companyservice.application.service.CompanyService;
import com.sparta.companyservice.domain.model.Company;
import com.sparta.companyservice.presentation.request.CreateCompanyRequest;
import com.sparta.companyservice.presentation.response.CreateCompanyResponse;
import com.sparta.companyservice.presentation.response.FindCompanyResponse;
import com.sparta.companyservice.presentation.response.GetCompanyResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/companies")
@Tag(name = "업체 API", description = "업체 관련 기능 API입니다.")
public class CompanyController {

	private final CompanyService companyService;

	/**
	 * 업체 생성 - 권한: HUB_MANAGER, MASTER
	 */
	@Operation(summary = "업체 생성", description = "업체를 생성합니다.")
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
	@Operation(summary = "업체 상세 조회", description = "업체를 상세 조회합니다.")
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
	@Operation(summary = "업체 다건 조회", description = "업체를 모두 조회합니다.")
	@GetMapping
	public ResponseEntity<ApiResponse<PageResponse<GetCompanyResponse>>> getCompanies(
		@RequestParam(defaultValue = "0") int page,
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(defaultValue = "createdAt,desc") String sort
	) {
		Sort.Direction direction = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

		Page<GetCompanyResult> results = companyService.getCompanies(page, size, direction);

		Page<GetCompanyResponse> responsePage = results.map(GetCompanyResponse::fromResult);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ApiResponse.success(PageResponse.of(responsePage)));
	}
}
