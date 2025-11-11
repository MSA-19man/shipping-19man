package com.sparta.userservice.user.presentation.response;

import java.time.LocalDateTime;

import com.sparta.userservice.user.domain.model.ApprovalStatus;
import com.sparta.userservice.user.domain.model.User;
import com.sparta.userservice.user.domain.model.UserRole;

import lombok.Builder;

@Builder
public record SignupUserResponse(
	Long userId,
	String username,
	String name,
	UserRole role,
	ApprovalStatus status,
	LocalDateTime createdAt,
	Long createdBy
) {
	public static SignupUserResponse from(User user) {
		return SignupUserResponse.builder()
			.userId(user.getId())
			.username(user.getUsername())
			.name(user.getName())
			.role(user.getRole())
			.status(user.getStatus())
			.createdAt(user.getCreatedAt())
			.createdBy(user.getCreatedBy())
			.build();
	}
}
