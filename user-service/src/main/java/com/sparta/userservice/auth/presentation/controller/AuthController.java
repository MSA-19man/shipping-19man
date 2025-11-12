package com.sparta.userservice.auth.presentation.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.common.response.ApiResponse;
import com.sparta.userservice.auth.application.dto.LoginCommand;
import com.sparta.userservice.auth.application.dto.LoginResult;
import com.sparta.userservice.auth.application.service.AuthService;
import com.sparta.userservice.auth.presentation.request.LoginRequest;
import com.sparta.userservice.auth.presentation.response.LoginResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
		LoginCommand command = request.toCommand();
		LoginResult result = authService.login(command);
		return ResponseEntity.status(HttpStatus.OK)
			.body(ApiResponse.success(LoginResponse.fromResult(result)));
	}
}
