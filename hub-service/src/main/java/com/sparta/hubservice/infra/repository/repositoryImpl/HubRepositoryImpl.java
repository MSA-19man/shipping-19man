package com.sparta.hubservice.infra.repository.repositoryImpl;

import com.sparta.hubservice.domain.model.Hub;
import com.sparta.hubservice.domain.repository.HubRepository;
import com.sparta.hubservice.infra.repository.JpaRepository.HubJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HubRepositoryImpl implements HubRepository {

    private final HubJpaRepository jpaRepository;

    @Override
    public Hub save(Hub hub){
        return jpaRepository.save(hub);
    }

    @Override
    public Page<Hub> findAll(Pageable pageable){
        return jpaRepository.findAll(pageable);
    }

    @Override
    public Optional<Hub> findById(UUID hubId){
        return jpaRepository.findById(hubId);
    }

    @Override
    public List<Hub> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<Hub> findAllByNameIn(List<String> names) {
        return jpaRepository.findAllByNameIn(names);
    }
}
