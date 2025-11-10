package com.sparta.deliveryservice.infrastructure.repository;

import com.sparta.deliveryservice.domain.model.Delivery;
import com.sparta.deliveryservice.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepository {
    private final DeliveryJpaRepository jpaRepository;

    @Override
    public Delivery save(Delivery delivery) {
        return jpaRepository.save(delivery);
    }

    @Override
    public boolean existsByOrderIdAndDeletedAtIsNull(UUID orderId) {
        return jpaRepository.existsByOrderIdAndDeletedAtIsNull(orderId);
    }

    @Override
    public Optional<Delivery> findByIdAndDeletedAtIsNull(UUID deliveryId) {
        return jpaRepository.findByIdAndDeletedAtIsNull(deliveryId);
    }

    @Override
    public Page<Delivery> findAllByDeletedAtIsNull(Pageable pageable) {
        return jpaRepository.findAllByDeletedAtIsNull(pageable);
    }

    @Override
    public Page<Delivery> findAllByUserIdAndDeletedAtIsNull(Long userId, Pageable pageable) {
        return jpaRepository.findAllByUserIdAndDeletedAtIsNull(userId, pageable);
    }

    @Override
    public Page<Delivery> findAllByDepartureHubIdOrArrivalHubIdAndDeletedAtIsNull(UUID departureHubId, UUID arrivalHubId, Pageable pageable) {
        return jpaRepository.findAllByDepartureHubIdOrArrivalHubIdAndDeletedAtIsNull(departureHubId, arrivalHubId, pageable);
    }
}
