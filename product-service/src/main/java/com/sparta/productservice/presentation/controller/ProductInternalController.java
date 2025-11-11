package com.sparta.productservice.presentation.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.productservice.application.service.ProductService;
import com.sparta.productservice.domain.model.Product;
import com.sparta.productservice.presentation.request.DeductStockRequest;
import com.sparta.productservice.presentation.response.DeductStockResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/v1/products")
public class ProductInternalController {

	private final ProductService productService;

	@PostMapping("/{productId}/stock/deduct")
	public DeductStockResponse deductStock(
		@PathVariable("productId") UUID productId,
		@Valid @RequestBody DeductStockRequest request
	) {
		Product updatedProduct = productService.deductStock(
			request.toCommand(productId)
		);

		return DeductStockResponse.from(updatedProduct);
	}
}
