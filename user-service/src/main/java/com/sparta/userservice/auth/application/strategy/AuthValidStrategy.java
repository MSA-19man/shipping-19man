package com.sparta.userservice.auth.application.strategy;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sparta.userservice.user.domain.model.ApprovalStatus;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthValidStrategy {

	private final PasswordEncoder passwordEncoder;

	// password 검증
	public void validPassword(String password, String encodedPassword) {
		if (!passwordEncoder.matches(password, encodedPassword)) {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}
	}

	// 가입 승인 상태(APPROVED) 검증
	public void validStatus(ApprovalStatus status) {
		if (!status.equals(ApprovalStatus.APPROVED)) {
			throw new RuntimeException("승인되지 않은 사용자입니다.");
		}
	}
}
