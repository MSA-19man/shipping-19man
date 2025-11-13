package com.sparta.orderservice.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.sparta.orderservice.domain.model.Order;

public interface OrderRepository {

	Order save(Order order);

	Optional<Order> findById(UUID orderId);
}
