package com.sparta.companyservice.infra.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	@Override
	public Optional<Company> findById(UUID companyId) {
		return companyJpaRepository.findByIdAndDeletedAtIsNull(companyId);
	}

	@Override
	public Page<Company> findAll(Pageable pageable) {
		return companyJpaRepository.findAllByDeletedAtIsNull(pageable);
	}
}
