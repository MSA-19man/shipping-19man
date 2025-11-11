package com.sparta.userservice.user.infra.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.userservice.user.domain.model.HubManager;

public interface HubManagerJpaRepository extends JpaRepository<HubManager, UUID> {
}
