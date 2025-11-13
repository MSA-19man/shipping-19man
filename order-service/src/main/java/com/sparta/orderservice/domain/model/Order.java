package com.sparta.orderservice.domain.model;

import java.util.UUID;

import com.sparta.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "p_order", schema = "order_schema")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private UUID supplyCompanyId;

	@Column(nullable = false)
	private UUID receiveCompanyId;

	@Column(nullable = false)
	private Long userId;

	@Column(nullable = false)
	private UUID productId;

	@Column(nullable = false)
	private int quantity;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	@Column
	private String message;

	@Builder(access = AccessLevel.PRIVATE)
	private Order(UUID supplyCompanyId, UUID receiveCompanyId, Long userId, UUID productId, int quantity,
		String message) {
		this.supplyCompanyId = supplyCompanyId;
		this.receiveCompanyId = receiveCompanyId;
		this.userId = userId;
		this.productId = productId;
		this.quantity = quantity;
		this.status = OrderStatus.PENDING;
		this.message = message;
	}

	public static Order of(UUID supplyCompanyId, UUID receiveCompanyId, Long userId, UUID productId, int quantity,
		String message) {
		return Order.builder()
			.supplyCompanyId(supplyCompanyId)
			.receiveCompanyId(receiveCompanyId)
			.userId(userId)
			.productId(productId)
			.quantity(quantity)
			.message(message)
			.build();
	}

	public void updateStatus(OrderStatus status) {
		this.status = status;
	}
}
