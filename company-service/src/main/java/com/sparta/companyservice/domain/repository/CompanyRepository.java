package com.sparta.companyservice.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.sparta.companyservice.domain.model.Company;

public interface CompanyRepository {

	Company save(Company company);

	boolean existsByName(String name);

	Optional<Company> findById(UUID companyId);
}
