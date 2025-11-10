package com.sparta.productservice.domain.repository;

import com.sparta.productservice.domain.model.Product;

public interface ProductRepository {
	Product save(Product product);
}
