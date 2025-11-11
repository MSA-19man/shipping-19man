package com.sparta.userservice.infra.repository;

import org.springframework.stereotype.Repository;

import com.sparta.userservice.domain.model.HubManager;
import com.sparta.userservice.domain.repository.HubManagerRepository;

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
