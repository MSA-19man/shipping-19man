package com.sparta.hubservice.infra.repository.repositoryImpl;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.sparta.hubservice.domain.model.Hub;
import com.sparta.hubservice.domain.repository.HubRepository;
import com.sparta.hubservice.infra.repository.JpaRepository.HubJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class HubRepositoryImpl implements HubRepository {

	private final HubJpaRepository jpaRepository;

	@Override
	public Hub save(Hub hub) {
		return jpaRepository.save(hub);
	}

	@Override
	public Page<Hub> findAll(Pageable pageable) {
		return jpaRepository.findAll(pageable);
	}

    @Override
    public Optional<Hub> findById(UUID hubId){
        return jpaRepository.findById(hubId);
    }

    // @Override
    // public List<Hub> findAll() {
    //     return jpaRepository.findAll();
    // }

    @Override
    public List<Hub> findAllByNameIn(List<String> names) {
        return jpaRepository.findAllByNameIn(names);
    }

	@Override
	public Boolean existsByIdAndDeletedAtIsNull(UUID hubId) {
		return jpaRepository.existsByIdAndDeletedAtIsNull(hubId);
	}

	@Cacheable(value = "hubs")
	@Override
	public List<Hub> findAll() {
		return jpaRepository.findAll();
	}
}
