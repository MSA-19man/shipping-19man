package com.sparta.hubservice.domain.repository;

import com.sparta.hubservice.domain.model.HubRoute;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface HubRouteRepository {

    //ApplicationRunner에서 사용 - 대량 저장
    void saveAll(List<HubRoute> hubRoutes);

    //ApplicationRunner에서 사용 - 데이터 존재 여부
    long count();

    // HubRouteService - 단건 저장
    HubRoute save(HubRoute hubRoute);

    // 단건 조회
    Optional<HubRoute> findById(UUID id);

    // Todo 출발/도착지 별로 조회하기..
}
