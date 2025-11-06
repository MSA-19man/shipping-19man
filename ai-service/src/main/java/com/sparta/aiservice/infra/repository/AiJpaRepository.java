package com.sparta.aiservice.infra.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.aiservice.domain.model.Ai;

public interface AiJpaRepository extends JpaRepository<Ai, UUID> {
	List<Ai> findByOrderId(UUID orderId, org.springframework.data.domain.Pageable pageable);
}