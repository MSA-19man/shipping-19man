package com.sparta.deliveryservice.domain.repository;

import com.sparta.deliveryservice.domain.model.DeliveryRoute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface DeliveryRouteRepository {
    DeliveryRoute save(DeliveryRoute deliveryRoute);
    Boolean findByDeliveryIdAndDeletedAtIsNull(UUID deliveryId);
    Page<DeliveryRoute> findAllByDeliveryIdAndDeletedAtIsNull(UUID deliveryId, Pageable pageable);
}
