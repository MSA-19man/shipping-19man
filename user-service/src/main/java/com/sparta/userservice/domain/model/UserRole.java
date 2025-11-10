package com.sparta.userservice.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

	MASTER(Role.MASTER, "마스터 관리자"),
	HUB_MANAGER(Role.HUB_MANAGER, "허브 관리자"),
	DELIVERY_MANAGER(Role.DELIVERY_MANAGER, "배송 담당자"),
	SUPPLIER_MANAGER(Role.SUPPLIER_MANAGER, "업체 담당자");

	private final String role;
	private final String description;

	private static class Role {
		public static final String MASTER = "ROLE_MASTER";
		public static final String HUB_MANAGER = "ROLE_HUB_MANAGER";
		public static final String DELIVERY_MANAGER = "ROLE_DELIVERY_MANAGER";
		public static final String SUPPLIER_MANAGER = "ROLE_SUPPLIER_MANAGER";
	}
}
