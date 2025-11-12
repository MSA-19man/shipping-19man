package com.sparta.hubservice.domain.repository;

import com.sparta.hubservice.domain.model.Hub;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;
import java.util.List;


public interface HubRepository {
	Hub save(Hub hub);

	Page<Hub> findAll(Pageable pageable);

    List<Hub> findAll();

    Optional<Hub> findById(UUID hubId);

    List<Hub> findAllByNameIn(List<String> names);

	Boolean existsByIdAndDeletedAtIsNull(UUID hubId);
}
