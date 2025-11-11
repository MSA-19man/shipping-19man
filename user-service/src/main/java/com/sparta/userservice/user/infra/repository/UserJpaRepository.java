package com.sparta.userservice.user.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.userservice.user.domain.model.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {

	boolean existsByUsername(String username);
}
