package com.sparta.orderservice.infra.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.sparta.orderservice.domain.model.Order;
import com.sparta.orderservice.domain.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

	private final OrderJpaRepository orderJpaRepository;

	@Override
	public Order save(Order order) {
		return orderJpaRepository.save(order);
	}

	@Override
	public Optional<Order> findById(UUID orderId) {
		return orderJpaRepository.findByIdAndDeletedAtIsNull(orderId);
	}
}
