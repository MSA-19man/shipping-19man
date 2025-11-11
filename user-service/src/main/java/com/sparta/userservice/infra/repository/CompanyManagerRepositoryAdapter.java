package com.sparta.userservice.infra.repository;

import org.springframework.stereotype.Repository;

import com.sparta.userservice.domain.model.CompanyManager;
import com.sparta.userservice.domain.repository.CompanyManagerRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CompanyManagerRepositoryAdapter implements CompanyManagerRepository {

	private final CompanyManagerJpaRepository companyManagerJpaRepository;

	@Override
	public CompanyManager save(CompanyManager companyManager) {
		return companyManagerJpaRepository.save(companyManager);
	}
}
