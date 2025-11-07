package com.sparta.deliveryservice.domain.repository;

import com.sparta.deliveryservice.domain.model.Delivery;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository {

	Delivery save(Delivery delivery);

	Optional<Delivery> findByOrderId(UUID orderId);

	Optional<Delivery> findById(UUID deliveryId);
}
