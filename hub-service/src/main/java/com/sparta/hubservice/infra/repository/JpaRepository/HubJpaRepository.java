package com.sparta.hubservice.infra.repository.JpaRepository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.hubservice.domain.model.Hub;

public interface HubJpaRepository extends JpaRepository<Hub, UUID> {
	Boolean existsByIdAndDeletedAtIsNull(UUID hubId);
}
