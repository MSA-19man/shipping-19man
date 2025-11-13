package com.sparta.userservice.auth.application.dto;

import lombok.Builder;

@Builder
public record LoginResult(
	String accessToken
) {
	public static LoginResult from(String accessToken) {
		return LoginResult.builder()
			.accessToken(accessToken)
			.build();
	}
}
