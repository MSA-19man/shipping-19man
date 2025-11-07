package com.sparta.companyservice.domain.model;

import java.util.UUID;

import com.sparta.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "p_company")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CompanyType type;

	@Column(nullable = false)
	private UUID hubId;

	@Column(nullable = false)
	private String companyAddress;

	@Builder(access = AccessLevel.PRIVATE)
	private Company(String name, CompanyType type, UUID hubId, String companyAddress) {
		this.name = name;
		this.type = type;
		this.hubId = hubId;
		this.companyAddress = companyAddress;
	}

	public static Company of(String name, CompanyType type, UUID hubId, String companyAddress) {
		return Company.builder()
			.name(name)
			.type(type)
			.hubId(hubId)
			.companyAddress(companyAddress)
			.build();
	}
}
