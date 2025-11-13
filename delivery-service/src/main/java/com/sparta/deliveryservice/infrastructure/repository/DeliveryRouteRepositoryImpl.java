package com.sparta.deliveryservice.infrastructure.repository;

import com.sparta.deliveryservice.domain.model.DeliveryRoute;
import com.sparta.deliveryservice.domain.repository.DeliveryRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DeliveryRouteRepositoryImpl implements DeliveryRouteRepository {

    private final DeliveryRouteJpaRepository jpaRepository;

    @Override
    public DeliveryRoute save(DeliveryRoute deliveryRoute) {
        return jpaRepository.save(deliveryRoute);
    }

    @Override
    public Boolean findByDeliveryIdAndDeletedAtIsNull(UUID deliveryId) {
        return jpaRepository.findByDeliveryIdAndDeletedAtIsNull(deliveryId);
    }

    @Override
    public Page<DeliveryRoute> findAllByDeliveryIdAndDeletedAtIsNull(UUID deliveryId, Pageable pageable) {
        return jpaRepository.findAllByDeliveryIdAndDeletedAtIsNull(deliveryId,pageable);
    }
}
