package com.sparta.common.security;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserPrincipal {

	private final Long userId;
	private final String role;

	public boolean hasRole(String role) {
		return this.role != null && this.role.equals(role);
	}

	public boolean hasAnyRole(String... roles) {
		if (this.role == null) {
			return false;
		}
		for (String role : roles) {
			if (this.role.equals(role)) {
				return true;
			}
		}
		return false;
	}
}
