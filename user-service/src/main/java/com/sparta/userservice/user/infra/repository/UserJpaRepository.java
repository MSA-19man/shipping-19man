package com.sparta.userservice.user.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.userservice.user.domain.model.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {

	boolean existsByUsername(String username);

	Optional<User> findByUsername(String username);
}
