package com.sparta.deliveryservice.presentation.controller;

import com.sparta.deliveryservice.application.service.DeliveryService;
import com.sparta.deliveryservice.presentation.request.DeliveryCreateRequest;
import com.sparta.deliveryservice.presentation.response.DeliveryCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

	private final DeliveryService deliveryService;

	@PostMapping
	public ResponseEntity<DeliveryCreateResponse> createDelivery(
		@RequestBody DeliveryCreateRequest request) {

		DeliveryCreateResponse response = deliveryService.createDelivery(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
