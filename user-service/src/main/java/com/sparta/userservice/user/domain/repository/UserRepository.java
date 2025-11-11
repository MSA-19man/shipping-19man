package com.sparta.userservice.user.domain.repository;

import com.sparta.userservice.user.domain.model.User;

public interface UserRepository {

	User save(User user);

	boolean existsByUsername(String username);
}
