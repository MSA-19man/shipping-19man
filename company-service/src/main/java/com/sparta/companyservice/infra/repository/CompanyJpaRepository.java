package com.sparta.companyservice.infra.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.companyservice.domain.model.Company;

public interface CompanyJpaRepository extends JpaRepository<Company, UUID> {
	boolean existsByNameAndDeletedAtIsNull(String name);
}
