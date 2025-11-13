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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Tag(name = "상품 API", description = "상품 관련 기능 API입니다.")
public class ProductController {

	private final ProductService productService;

	@Operation(summary = "상품 생성", description = "상품을 생성할 수 있습니다.")
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

