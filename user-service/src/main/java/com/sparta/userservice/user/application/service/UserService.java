package com.sparta.userservice.user.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.userservice.user.application.dto.SignupUserCommand;
import com.sparta.userservice.user.application.strategy.PasswordService;
import com.sparta.userservice.user.application.strategy.ValidDtoStrategy;
import com.sparta.userservice.user.domain.model.User;
import com.sparta.userservice.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

	private final UserRepository userRepository;

	private final ValidDtoStrategy validDtoStrategy;
	private final PasswordService passwordService;

	@Transactional
	public User signup(SignupUserCommand command) {

		validDtoStrategy.validUsername(command.username());

		validDtoStrategy.validateRoleSpecificData(command.role(), command.hubId(), command.companyId());

		User savedUser = command.toEntity(passwordService.hashPassword(command.password()));

		userRepository.save(savedUser);

		validDtoStrategy.createRoleSpecificEntity(savedUser, command.hubId(), command.companyId());

		return savedUser;
	}
}
