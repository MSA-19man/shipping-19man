package com.sparta.aiservice.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sparta.aiservice.domain.model.Ai;

public interface AiRepository {
	Ai save(Ai ai);

	Optional<Ai> findById(UUID id);

	List<Ai> findAllByOrderId(UUID orderId, int page, int size);

	void deleteById(UUID id);
}