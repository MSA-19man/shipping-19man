package com.sparta.deliveryservice.domain.repository;

import com.sparta.deliveryservice.domain.model.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface DeliveryRepository {

    Delivery save(Delivery delivery);

    boolean existsByOrderIdAndDeletedAtIsNull(UUID orderId);

    Optional<Delivery> findByIdAndDeletedAtIsNull(UUID deliveryId);

    /**
     * MASTER - 전체 조회
     */
    Page<Delivery> findAllByDeletedAtIsNull(Pageable pageable);

    /**
     * 주문한 업체 담당자 - userId로 조회
     * 배송 담당자 - userId로 조회
     */
    Page<Delivery> findAllByUserIdAndDeletedAtIsNull(Long userId, Pageable pageable);

    /**
     * 허브 담당자 - 출발 허브 OR 도착 허브로 조회
     */
    Page<Delivery> findAllByDepartureHubIdOrArrivalHubIdAndDeletedAtIsNull(UUID departureHubId, UUID arrivalHubId, Pageable pageable);
}
