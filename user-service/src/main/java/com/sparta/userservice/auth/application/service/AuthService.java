package com.sparta.userservice.auth.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.userservice.auth.application.dto.LoginCommand;
import com.sparta.userservice.auth.application.dto.LoginResult;
import com.sparta.userservice.auth.application.strategy.AuthValidStrategy;
import com.sparta.userservice.auth.domain.repository.AuthRepository;
import com.sparta.userservice.auth.infra.jwt.JwtTokenProvider;
import com.sparta.userservice.auth.infra.jwt.TokenResponse;
import com.sparta.userservice.user.domain.model.User;
import com.sparta.userservice.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

	private final AuthRepository authRepository; // refresh token 저장용. 현재는 access token만 적용할 예정
	private final UserRepository userRepository;
	private final AuthValidStrategy authValidStrategy;
	private final JwtTokenProvider jwtTokenProvider;

	@Transactional
	public LoginResult login(LoginCommand command) {

		/** username으로 유저 정보 가져오기*/
		User foundUser = userRepository.findByUsername(command.username()); // 리팩토링 예정

		/** 가져온 유저정보로 저장된 비밀번호와 입력된 비밀번호 검증*/
		authValidStrategy.validPassword(command.password(), foundUser.getPassword());

		/** 가입 승인 상태가 APPROVED인지 검증*/
		authValidStrategy.validStatus(foundUser.getStatus());

		/** jwt 발급*/
		TokenResponse tokenResponse = jwtTokenProvider
			.generateToken(foundUser.getId(), foundUser.getRole().toString());

		return LoginResult.from(tokenResponse.accessToken());
	}
}
