package com.sparta.companyservice.infra.repository;

import org.springframework.stereotype.Repository;

import com.sparta.companyservice.domain.model.Company;
import com.sparta.companyservice.domain.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CompanyRepositoryImpl implements CompanyRepository {

	private final CompanyJpaRepository companyJpaRepository;

	@Override
	public Company save(Company company) {
		return companyJpaRepository.save(company);
	}

	@Override
	public boolean existsByName(String name) {
		return companyJpaRepository.existsByNameAndDeletedAtIsNull(name);
	}
}
