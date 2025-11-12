package com.sparta.userservice.auth.presentation.response;

import com.sparta.userservice.auth.application.dto.LoginResult;

import lombok.Builder;

@Builder
public record LoginResponse(
	String accessToken
) {
	public static LoginResponse fromResult(LoginResult result) {
		return LoginResponse.builder()
			.accessToken(result.accessToken())
			.build();
	}
}
