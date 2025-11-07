package com.sparta.deliveryservice.infrastructure.repository;

import com.sparta.deliveryservice.domain.model.Delivery;
import com.sparta.deliveryservice.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepository {
	private final DeliveryJpaRepository jpaRepository;

	@Override
	public Delivery save(Delivery delivery) {
		return jpaRepository.save(delivery);
	}

	@Override
	public Optional<Delivery> findByOrderId(UUID orderId) {
		return jpaRepository.findByOrderId(orderId);
	}
}
