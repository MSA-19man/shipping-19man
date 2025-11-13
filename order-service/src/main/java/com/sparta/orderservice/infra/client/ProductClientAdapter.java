package com.sparta.orderservice.infra.client;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sparta.orderservice.domain.client.ProductClient;
import com.sparta.orderservice.domain.client.dto.DeductStockRequest;
import com.sparta.orderservice.domain.client.dto.ProductStockInfo;
import com.sparta.orderservice.domain.client.dto.ReceiveStockRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductClientAdapter implements ProductClient {

	private final ProductFeignClient productFeignClient;

	@Override
	public ProductStockInfo getProductStock(UUID productId) {
		return productFeignClient.getProduct(productId);
	}

	@Override
	public void deductStock(UUID productId, Integer quantity) {
		DeductStockRequest request = DeductStockRequest.builder().
			quantity(quantity)
			.build();
		productFeignClient.deductStock(productId, request);
	}

	@Override
	public void receiveStock(UUID productId, Integer quantity) {
		ReceiveStockRequest request = ReceiveStockRequest.builder()
			.quantity(quantity)
			.build();
		productFeignClient.receiveStock(productId, request);
	}
}
