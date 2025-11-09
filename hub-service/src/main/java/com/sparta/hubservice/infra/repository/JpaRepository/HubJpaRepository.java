package com.sparta.hubservice.infra.repository.JpaRepository;

import com.sparta.hubservice.domain.model.Hub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HubJpaRepository extends JpaRepository<Hub, UUID> {
}
