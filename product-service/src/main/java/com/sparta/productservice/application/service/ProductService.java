package com.sparta.productservice.application.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.productservice.application.dto.CreateProductCommand;
import com.sparta.productservice.domain.model.Product;
import com.sparta.productservice.domain.repository.ProductRepository;
import com.sparta.productservice.infra.client.CompanyClient;
import com.sparta.productservice.infra.client.dto.CompanyResponse;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

	private final ProductRepository productRepository;
	private final CompanyClient companyClient;

	@Transactional
	public Product createProduct(CreateProductCommand command) {
		CompanyResponse response = validateCompany(command);
		validateHub(command, response);

		Product product = Product.of(
			command.name(),
			command.companyId(),
			command.hubId(),
			command.stock()
		);

		Product savedProduct = productRepository.save(product);

		log.info("[ProductService] 상품 생성 완료 - productId={}", savedProduct.getId());

		return savedProduct;
	}

	private CompanyResponse validateCompany(CreateProductCommand command) {
		try {
			return companyClient.getCompany(command.companyId());

		} catch (FeignException.NotFound e) {
			throw new IllegalArgumentException("존재하지 않는 업체 ID 입니다. companyId: " + command.companyId());
		} catch (Exception e) {
			log.error("Company-Service 호출 중 오류 발생: {}", e.getMessage());
			throw new IllegalStateException("업체 정보 호출중 오류", e);
		}
	}

	private void validateHub(CreateProductCommand command, CompanyResponse response) {
		if (!Objects.equals(response.hubId(), command.hubId())) {
			throw new IllegalArgumentException("상품 재고는 업체 관리 허브에만 등록 가능합니다.");
		}
	}

}
