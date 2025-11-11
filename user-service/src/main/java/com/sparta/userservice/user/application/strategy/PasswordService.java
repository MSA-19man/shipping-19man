package com.sparta.userservice.user.application.strategy;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PasswordService {

	private final PasswordEncoder passwordEncoder;

	public String hashPassword(String password) {
		return passwordEncoder.encode(password);
	}
}
