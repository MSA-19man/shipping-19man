package com.sparta.common.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.sparta.common.security.UserContextHolder;
import com.sparta.common.security.UserPrincipal;

@Component
public class AuditorAwareImpl implements AuditorAware<Long> {
	@Override
	public Optional<Long> getCurrentAuditor() {
		UserPrincipal user = UserContextHolder.getUser();

		if (user == null || user.getUserId() == null) {
			return Optional.empty();
		}
		return Optional.of(user.getUserId());
	}
}
