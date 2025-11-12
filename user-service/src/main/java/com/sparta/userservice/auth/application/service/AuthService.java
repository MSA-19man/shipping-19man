package com.sparta.userservice.auth.application.service;

import org.springframework.stereotype.Service;

import com.sparta.userservice.auth.domain.repository.AuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthRepository authRepository;

}
