package com.sparta.userservice.user.presentation.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sparta.common.response.ApiResponse;
import com.sparta.userservice.user.application.service.UserService;
import com.sparta.userservice.user.domain.model.User;
import com.sparta.userservice.user.presentation.request.SignupUserRequest;
import com.sparta.userservice.user.presentation.response.SignupUserResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<ApiResponse<SignupUserResponse>> signup(
		@RequestBody @Valid SignupUserRequest request
	) {
		User signupUser = userService.signup(request.toCommand());

		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
			.path("/{userId}")
			.buildAndExpand(signupUser.getId())
			.toUri();

		return ResponseEntity.created(location)
			.body(ApiResponse.success(SignupUserResponse.from(signupUser), "회원가입 신청이 완료되었습니다."));
	}
}
