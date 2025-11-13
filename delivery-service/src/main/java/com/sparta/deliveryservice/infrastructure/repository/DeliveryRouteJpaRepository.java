package com.sparta.deliveryservice.infrastructure.repository;

import com.sparta.deliveryservice.domain.model.DeliveryRoute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeliveryRouteJpaRepository extends JpaRepository<DeliveryRoute, UUID> {
    boolean findByDeliveryIdAndDeletedAtIsNull(UUID deliveryId);

    Page<DeliveryRoute> findAllByDeliveryIdAndDeletedAtIsNull(UUID deliveryId, Pageable pageable);
}
