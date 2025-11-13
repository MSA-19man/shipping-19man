package com.sparta.orderservice.application.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.orderservice.domain.model.Order;
import com.sparta.orderservice.domain.model.OrderStatus;
import com.sparta.orderservice.domain.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

	private final OrderRepository orderRepository;

	@Transactional
	public Order saveOrder(Order order) {
		log.info("[OrderService] 주문 저장 - orderId={}", order.getId());
		return orderRepository.save(order);
	}

	public Order findById(UUID orderId) {
		return orderRepository.findById(orderId)
			.orElseThrow(
				() -> new IllegalArgumentException("해당 주문을 찾을 수 없습니다. orderId=" + orderId)
			);
	}

	@Transactional
	public void updateStatus(UUID orderId, OrderStatus status) {
		log.info("[OrderService] 주문 상태 변경 - orderId={}, status={}", orderId, status);
		Order order = findById(orderId);
		order.updateStatus(status);
	}
}
