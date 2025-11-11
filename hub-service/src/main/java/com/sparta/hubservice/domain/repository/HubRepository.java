package com.sparta.hubservice.domain.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sparta.hubservice.domain.model.Hub;

public interface HubRepository {
	Hub save(Hub hub);

	Page<Hub> findAll(Pageable pageable);

	Optional<Hub> findById(UUID hubId);

	Boolean existsByIdAndDeletedAtIsNull(UUID hubId);
}
