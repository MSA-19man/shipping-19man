package com.sparta.userservice.auth.presentation.request;

import com.sparta.userservice.auth.application.dto.LoginCommand;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
	@NotBlank(message = "아이디를 입력해주세요.")
	String username,
	@NotBlank(message = "비밀번호를 입력해주세요.")
	String password
) {
	public LoginCommand toCommand() {
		return LoginCommand.builder()
			.username(username)
			.password(password)
			.build();
	}
}
