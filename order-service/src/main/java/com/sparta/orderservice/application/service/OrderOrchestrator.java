package com.sparta.orderservice.application.service;

import com.sparta.orderservice.application.dto.CreateOrderCommand;
import com.sparta.orderservice.domain.client.CompanyClient;
import com.sparta.orderservice.domain.client.DeliveryMessagePublisher;
import com.sparta.orderservice.domain.client.ProductClient;
import com.sparta.orderservice.domain.client.dto.CompanyInfo;
import com.sparta.orderservice.domain.client.dto.ProductStockInfo;
import com.sparta.orderservice.domain.model.Order;
import com.sparta.orderservice.domain.model.OrderStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderOrchestrator {

	private final OrderService orderService;
	private final ProductClient productClient;
	private final CompanyClient companyClient;
	private final DeliveryMessagePublisher deliveryMessagePublisher;

	public Order executeOrder(Long userId, CreateOrderCommand command) {
		boolean stockDeducted = false;
		boolean orderCreated = false;
		UUID orderId = null;
		CompanyInfo supplier = null;
		CompanyInfo receiver = null;

		try {
			// 공급 업체 검증
			supplier = companyClient.getCompany(command.supplyCompanyId());

			if (supplier.type() != CompanyInfo.CompanyType.PRODUCER) {
				throw new IllegalArgumentException("공급 업체가 아닙니다");
			}

			receiver = companyClient.getCompany(command.receiveCompanyId());

			// 수령 업체 검증
			if (receiver.type() != CompanyInfo.CompanyType.RECEIVER) {
				throw new IllegalArgumentException("수령 업체가 아닙니다");
			}

			// 재고 확인
			ProductStockInfo product = productClient.getProductStock(command.productId());

			if (product.stock() < command.quantity()) {
				throw new IllegalArgumentException("해당 상품의 재고가 부족합니다." + command.productId());
			}
			// 재고 차감
			productClient.deductStock(command.productId(), command.quantity());
			stockDeducted = true;

			// 주문 생성
			Order order = command.toEntity(userId);
			Order savedOrder = orderService.saveOrder(order);
			orderId = savedOrder.getId();
			orderCreated = true;

			// 주문 확정
			orderService.updateStatus(orderId, OrderStatus.CONFIRMED);

			// 메시지 발행
			deliveryMessagePublisher.publishDeliveryCreation(
				orderId,
				userId,
				supplier.hubId(),
				receiver.hubId(),
				receiver.companyAddress(),
				receiver.name()
			);
			log.info("orderId : {}, userId :{}, hubId: {}, address: {}, name: {}"
				, orderId, userId, receiver.hubId(), receiver.companyAddress(), receiver.name());

			log.info("주문 성공: orderId: {}", savedOrder.getId());
			return savedOrder;

		} catch (Exception e) {
			// 보상 트랜잭션
			executeCompensation(orderId, orderCreated, stockDeducted, command);

			throw new RuntimeException("주문 생성 실패: " + e.getMessage(), e);
		}
	}

	private void executeCompensation(UUID orderId, boolean orderCreated,
		boolean stockDeducted, CreateOrderCommand command) {

		try {
			// 주문 생성 성공했으면 주문 취소
			if (orderCreated && orderId != null) {
				orderService.updateStatus(orderId, OrderStatus.FAILED);
			}

			// 재고 차감 성공했으면 재고 복구
			if (stockDeducted) {
				productClient.receiveStock(command.productId(), command.quantity());
			}

		} catch (Exception e) {
			log.error("보상 트랜잭션 실패 - 수동 개입 필요", e);
		}
	}
}
