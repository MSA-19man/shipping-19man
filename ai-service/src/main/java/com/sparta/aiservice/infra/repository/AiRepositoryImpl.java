package com.sparta.aiservice.infra.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.sparta.aiservice.domain.model.Ai;
import com.sparta.aiservice.domain.repository.AiRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AiRepositoryImpl implements AiRepository {

	private final AiJpaRepository jpaRepository;

	@Override
	public Ai save(Ai ai) {
		return jpaRepository.save(ai);
	}

	@Override
	public Optional<Ai> findById(UUID id) {
		return jpaRepository.findById(id);
	}

	@Override
	public List<Ai> findAllByOrderId(UUID orderId, int page, int size) {
		return jpaRepository.findByOrderId(orderId, PageRequest.of(page, size));
	}

	@Override
	public void deleteById(UUID id) {
		jpaRepository.deleteById(id);
	}
}
