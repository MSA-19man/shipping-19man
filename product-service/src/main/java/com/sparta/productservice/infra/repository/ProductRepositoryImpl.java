package com.sparta.productservice.infra.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.sparta.productservice.domain.model.Product;
import com.sparta.productservice.domain.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

	private final ProductJpaRepository productJpaRepository;

	@Override
	public Product save(Product product) {
		return productJpaRepository.save(product);
	}

	@Override
	public Optional<Product> findById(UUID id) {
		return productJpaRepository.findById(id);
	}
}
