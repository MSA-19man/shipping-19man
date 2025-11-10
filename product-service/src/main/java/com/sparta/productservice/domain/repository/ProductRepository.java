package com.sparta.productservice.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.sparta.productservice.domain.model.Product;

public interface ProductRepository {
	Product save(Product product);

	Optional<Product> findById(UUID id);
}
