package com.sparta.orderservice.infra.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sparta.orderservice.domain.client.dto.DeductStockRequest;
import com.sparta.orderservice.domain.client.dto.ProductStockInfo;
import com.sparta.orderservice.domain.client.dto.ReceiveStockRequest;

@FeignClient(name = "product-service")
public interface ProductFeignClient {

	@GetMapping("/internal/v1/products/{productId}/stock")
	ProductStockInfo getProduct(@PathVariable("productId") UUID productId);

	@PostMapping("/internal/v1/products/{productId}/stock/deduct")
	void deductStock(
		@PathVariable("productId") UUID productId,
		@RequestBody DeductStockRequest request
	);

	@PostMapping("/internal/v1/products/{productId}/stock/receive")
	void receiveStock(
		@PathVariable("productId") UUID productId,
		@RequestBody ReceiveStockRequest request
	);
}