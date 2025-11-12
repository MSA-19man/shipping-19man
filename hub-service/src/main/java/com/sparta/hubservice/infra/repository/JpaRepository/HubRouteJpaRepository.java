package com.sparta.hubservice.infra.repository.JpaRepository;

import com.sparta.hubservice.domain.model.HubRoute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HubRouteJpaRepository extends JpaRepository<HubRoute, UUID> {
}
