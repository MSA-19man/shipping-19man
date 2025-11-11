package com.sparta.userservice.user.application.dto;

import java.util.UUID;

import com.sparta.userservice.user.domain.model.User;
import com.sparta.userservice.user.domain.model.UserRole;

import lombok.Builder;

@Builder
public record SignupUserCommand(
	String username,
	String password,
	String name,
	UserRole role,
	UUID hubId,
	UUID companyId
) {
	public User toEntity(String encodedPassword) {
		return User.of(
			this.username,
			encodedPassword,
			this.name,
			this.role
		);
	}
}
