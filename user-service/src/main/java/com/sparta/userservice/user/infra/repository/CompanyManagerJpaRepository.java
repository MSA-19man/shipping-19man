package com.sparta.userservice.user.infra.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.userservice.user.domain.model.CompanyManager;

public interface CompanyManagerJpaRepository extends JpaRepository<CompanyManager, UUID> {
}
