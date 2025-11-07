package com.sparta.deliveryservice.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.sparta.deliveryservice.domain.model.Delivery;

public interface DeliveryRepository {

	Delivery save(Delivery delivery);

	Optional<Delivery> findByOrderId(UUID orderId);
}
