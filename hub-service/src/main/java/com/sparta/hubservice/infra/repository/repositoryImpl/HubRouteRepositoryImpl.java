package com.sparta.hubservice.infra.repository.repositoryImpl;

import com.sparta.hubservice.domain.model.HubRoute;
import com.sparta.hubservice.domain.repository.HubRouteRepository;
import com.sparta.hubservice.infra.repository.JpaRepository.HubRouteJpaRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class HubRouteRepositoryImpl implements HubRouteRepository {

    private final HubRouteJpaRepository jpaRepository;

    @Override
    public void saveAll(List<HubRoute> hubRoutes){
        jpaRepository.saveAll(hubRoutes);
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public HubRoute save(HubRoute hubRoute){
        return jpaRepository.save(hubRoute);
    }

    @Override
    public Optional<HubRoute> findById(UUID id){
        return jpaRepository.findById(id);
    }

    // @Override
    // public List<HubRoute> findAll() {
    //     return jpaRepository.findAll();
    // }

    @Cacheable(value = "hubRoutes")
    @Override
    public List<HubRoute> findAll(){
        return jpaRepository.findAll();
    }
}
