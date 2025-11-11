package com.sparta.hubservice.application.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.hubservice.application.dto.CreateHubCommand;
import com.sparta.hubservice.application.dto.CreateHubResult;
import com.sparta.hubservice.application.dto.FindHubResult;
import com.sparta.hubservice.domain.model.Hub;
import com.sparta.hubservice.domain.repository.HubRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class HubService {

	private final HubRepository hubRepository;
	private final HubRouteService hubRouteService;

	@Transactional
	public CreateHubResult createHub(CreateHubCommand command) {
		// Todo 허브 이름 중복 검사

		Hub newHub = Hub.of(
			command.name(),
			command.address(),
			command.latitude(),
			command.longitude()
		);

		Hub savedHub = hubRepository.save(newHub);
		log.info("허브 생성 완료 - hub id: " + savedHub.getId());

		// 새로 생긴 허브-중앙 허브 경로(segment) 생성
		hubRouteService.createSegmentsForNewHub(savedHub);

		return CreateHubResult.from(savedHub);
	}

	public Page<FindHubResult> getHubs(Pageable pageable) {
		Page<Hub> hubPage = hubRepository.findAll(pageable);

		// Page<Hub>(엔티티)를 Page<FindHubResult>(DTO)로 변환
		return hubPage.map(FindHubResult::from);
	}

	public FindHubResult getHubById(UUID hubId) {
		Hub hub = hubRepository.findById(hubId)
			.orElseThrow(() -> new IllegalArgumentException("해당 허브를 찾을 수 없습니다. Id: " + hubId));

		return FindHubResult.from(hub);
	}

	public Boolean existHub(UUID hubId) {
		return hubRepository.existsByIdAndDeletedAtIsNull(hubId);
	}
}
