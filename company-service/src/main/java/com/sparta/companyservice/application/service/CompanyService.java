package com.sparta.companyservice.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.companyservice.application.dto.CreateCompanyCommand;
import com.sparta.companyservice.application.dto.FindCompanyResult;
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
		log.info("[CompanyService] 업체 생성 시작 - name={}", command.name());

		if (companyRepository.existsByName(command.name())) {
			throw new IllegalArgumentException("이미 존재하는 업체명입니다.");
		}

		Company company = Company.of(
			command.name(),
			command.type(),
			command.hubId(),
			command.companyAddress()
		);

		Company savedCompany = companyRepository.save(company);
		log.info("[CompanyService] 업체 생성 완료 - companyId={}", savedCompany.getId());
		return savedCompany;
	}

	public FindCompanyResult getCompany(UUID companyId) {
		Company company = companyRepository.findById(companyId)
			.orElseThrow(() -> new IllegalArgumentException("해당 업체를 찾을 수 없습니다."));
		return FindCompanyResult.from(company);
	}
}
