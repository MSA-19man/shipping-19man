package com.sparta.productservice.domain.model;

import java.util.UUID;

import com.sparta.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "p_product", schema = "product_schema")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false)
	private UUID companyId;

	@Column(nullable = false)
	private UUID hubId;

	@Column(nullable = false)
	private Integer stock;

	@Version
	private Long version;

	@Builder(access = AccessLevel.PRIVATE)
	private Product(String name, UUID companyId, UUID hubId, Integer stock) {
		this.name = name;
		this.companyId = companyId;
		this.hubId = hubId;
		this.stock = stock;
	}

	public static Product of(String name, UUID companyId, UUID hubId, Integer stock) {
		return Product.builder()
			.name(name)
			.companyId(companyId)
			.hubId(hubId)
			.stock(stock)
			.build();
	}
}
