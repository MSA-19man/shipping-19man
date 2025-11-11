package com.sparta.companyservice.infra.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.companyservice.domain.model.Company;

public interface CompanyJpaRepository extends JpaRepository<Company, UUID> {
	boolean existsByNameAndDeletedAtIsNull(String name);

	Optional<Company> findByIdAndDeletedAtIsNull(UUID companyId);

	Page<Company> findAllByDeletedAtIsNull(Pageable pageable);

	Boolean existsByIdAndDeletedAtIsNull(UUID companyId);
}
