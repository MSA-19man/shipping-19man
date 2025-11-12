package com.sparta.hubservice.infra.repository.JpaRepository;

import com.sparta.hubservice.domain.model.Hub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HubJpaRepository extends JpaRepository<Hub, UUID> {

    List<Hub> findAllByNameIn(List<String> names);

    Boolean existsByIdAndDeletedAtIsNull(UUID hubId);
}
