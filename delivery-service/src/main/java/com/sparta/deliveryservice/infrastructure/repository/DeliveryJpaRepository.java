package com.sparta.deliveryservice.infrastructure.repository;

import com.sparta.deliveryservice.domain.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryJpaRepository extends JpaRepository<Delivery, UUID> {
	Optional<Delivery> findByOrderId(UUID orderId);
}
