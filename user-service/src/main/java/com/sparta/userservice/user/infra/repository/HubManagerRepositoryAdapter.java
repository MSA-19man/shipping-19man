package com.sparta.userservice.user.infra.repository;

import org.springframework.stereotype.Repository;

import com.sparta.userservice.user.domain.model.HubManager;
import com.sparta.userservice.user.domain.repository.HubManagerRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HubManagerRepositoryAdapter implements HubManagerRepository {

	private final HubManagerJpaRepository hubManagerJpaRepository;

	@Override
	public HubManager save(HubManager hubManager) {
		return hubManagerJpaRepository.save(hubManager);
	}
}
