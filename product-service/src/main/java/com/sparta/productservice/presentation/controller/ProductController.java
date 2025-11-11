package com.sparta.productservice.presentation.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sparta.common.response.ApiResponse;
import com.sparta.productservice.application.service.ProductService;
import com.sparta.productservice.domain.model.Product;
import com.sparta.productservice.presentation.request.CreateProductRequest;
import com.sparta.productservice.presentation.response.CreateProductResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
public class ProductController {

	private final ProductService productService;

	@PostMapping
	public ResponseEntity<ApiResponse<CreateProductResponse>> createProduct(
		@Valid @RequestBody CreateProductRequest request) {
		Product product = productService.createProduct(request.toCommand());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{productId}")
			.buildAndExpand(product.getId())
			.toUri();

		return ResponseEntity.created(location)
			.body(ApiResponse.success(
				CreateProductResponse.from(product),
				"상품 생성을 성공 했습니다.")
			);
	}
}

