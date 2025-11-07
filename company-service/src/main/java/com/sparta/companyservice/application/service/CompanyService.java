package com.sparta.companyservice.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.companyservice.application.dto.CreateCompanyCommand;
import com.sparta.companyservice.domain.model.Company;
import com.sparta.companyservice.domain.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CompanyService {

	private final CompanyRepository companyRepository;

	@Transactional
	public Company createCompany(CreateCompanyCommand command) {
		log.info("[CompanyService] 업체 생성 시작 - name={}", command.getName());

		if (companyRepository.existsByName(command.getName())) {
			throw new IllegalArgumentException("이미 존재하는 업체명입니다.");
		}

		Company company = Company.of(
			command.getName(),
			command.getType(),
			command.getHubId(),
			command.getCompanyAddress()
		);

		Company savedCompany = companyRepository.save(company);

		log.info("[CompanyService] 업체 생성 완료 - companyId={}", savedCompany.getId());

		// Domain 반환! (Response 아님)
		return savedCompany;
	}
}
