package com.sparta.userservice.user.application.strategy;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.sparta.userservice.user.domain.model.CompanyManager;
import com.sparta.userservice.user.domain.model.HubManager;
import com.sparta.userservice.user.domain.model.User;
import com.sparta.userservice.user.domain.model.UserRole;
import com.sparta.userservice.user.domain.repository.CompanyManagerRepository;
import com.sparta.userservice.user.domain.repository.HubManagerRepository;
import com.sparta.userservice.user.domain.repository.UserRepository;
import com.sparta.userservice.user.infra.client.CompanyClient;
import com.sparta.userservice.user.infra.client.HubClient;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ValidDtoStrategy {

	private final UserRepository userRepository;
	private final HubManagerRepository hubManagerRepository;
	private final CompanyManagerRepository companyManagerRepository;
	private final HubClient hubClient;
	private final CompanyClient companyClient;

	// username 중복 검증
	public void validUsername(String username) {
		if (userRepository.existsByUsername(username)) {
			throw new RuntimeException("이미 사용중인 아이디입니다.");
		}
	}

	// 허브 id, 업체 id 검증
	public void validateRoleSpecificData(UserRole role, UUID hubId, UUID companyId) {
		switch (role) {
			case HUB_MANAGER -> {
				if (hubId == null) {
					throw new RuntimeException("허브 관리자는 허브 ID가 필요합니다.");
				}
				if (!hubClient.existByHubId(hubId)) {
					throw new RuntimeException("존재하지 않는 허브입니다.");
				}
			}
			case SUPPLIER_MANAGER -> {
				if (companyId == null) {
					throw new RuntimeException("업체 담당자는 ID가 필요합니다.");
				}
				if (!companyClient.existByCompanyId(companyId)) {
					throw new RuntimeException("존재하지 않는 업체입니다.");
				}
			}
		}
	}

	// 허브관리자, 업체담당자 객체 생성 후 저장
	public void createRoleSpecificEntity(User user, UUID hubId, UUID companyId) {
		if (user.getRole().equals(UserRole.HUB_MANAGER)) {
			hubManagerRepository.save(HubManager.of(hubId, user));
		}
		if (user.getRole().equals(UserRole.SUPPLIER_MANAGER)) {
			companyManagerRepository.save(CompanyManager.of(companyId, user));
		}
	}
}
