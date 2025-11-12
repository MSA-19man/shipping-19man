package com.sparta.userservice.auth.application.dto;

import lombok.Builder;

@Builder
public record LoginCommand(
	String username,
	String password
) {
}
