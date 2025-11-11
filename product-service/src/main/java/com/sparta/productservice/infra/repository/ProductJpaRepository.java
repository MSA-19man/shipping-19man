package com.sparta.productservice.infra.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.productservice.domain.model.Product;

public interface ProductJpaRepository extends JpaRepository<Product, UUID> {
}
