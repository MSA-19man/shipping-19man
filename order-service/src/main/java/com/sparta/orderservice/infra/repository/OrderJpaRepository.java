package com.sparta.orderservice.infra.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.orderservice.domain.model.Order;

public interface OrderJpaRepository extends JpaRepository<Order, UUID> {
	Optional<Order> findByIdAndDeletedAtIsNull(UUID id);
}
