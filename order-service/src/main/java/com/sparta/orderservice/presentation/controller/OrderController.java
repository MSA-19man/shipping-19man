package com.sparta.orderservice.presentation.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sparta.common.response.ApiResponse;
import com.sparta.orderservice.application.service.OrderOrchestrator;
import com.sparta.orderservice.domain.model.Order;
import com.sparta.orderservice.presentation.request.CreateOrderRequest;
import com.sparta.orderservice.presentation.response.CreateOrderResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Tag(name = "주문 API", description = "주문 관련 기능 API입니다.")
public class OrderController {

	private final OrderOrchestrator orderOrchestrator;

	/**
	 * 주문 생성
	 * 권한: 업체 담당자
	 */
	@Operation(summary = "주문 생성", description = "주문 생성 후 배송쪽으로 메시지를 보냅니다.")
	@PostMapping
	public ResponseEntity<ApiResponse<CreateOrderResponse>> createOrder(
		@Valid @RequestBody CreateOrderRequest request
		// TODO: CurrentUser Long userId;
	) {
		Long userId = 1L;
		Order order = orderOrchestrator.executeOrder(userId, request.toCommand());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{orderId}")
			.buildAndExpand(order.getId())
			.toUri();

		return ResponseEntity.created(location)
			.body(
				ApiResponse.success(
					CreateOrderResponse.from(order),
					"주문 생성을 성공 했습니다.")
			);
	}
}

