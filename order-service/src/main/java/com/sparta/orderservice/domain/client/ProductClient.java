package com.sparta.orderservice.domain.client;

import java.util.UUID;

import com.sparta.orderservice.domain.client.dto.ProductStockInfo;

public interface ProductClient {
	ProductStockInfo getProductStock(UUID productId);

	void deductStock(UUID productId, Integer quantity);

	void receiveStock(UUID productId, Integer quantity);
}
