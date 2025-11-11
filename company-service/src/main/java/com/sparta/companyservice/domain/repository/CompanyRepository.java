package com.sparta.companyservice.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sparta.companyservice.domain.model.Company;

public interface CompanyRepository {

	Company save(Company company);

	boolean existsByName(String name);

	Optional<Company> findById(UUID companyId);

	Page<Company> findAll(Pageable pageable);

	Boolean existsByIdAndDeletedAtIsNull(UUID companyId);
}
