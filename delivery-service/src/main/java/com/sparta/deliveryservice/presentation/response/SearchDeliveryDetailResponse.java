package com.sparta.deliveryservice.presentation.response;

import com.sparta.deliveryservice.application.dto.SearchDeliveryDetailResult;
import com.sparta.deliveryservice.domain.model.DeliveryStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record SearchDeliveryDetailResponse(
        UUID deliveryId,
        UUID orderId,
        Long userId,
        DeliveryStatus status,
        UUID departureHubId,
        UUID arrivalHubId,
        String deliveryAddress,
        String receiverName,
        String receiverSlackId,
        UUID companyAgentId,
        LocalDateTime createdAt,
        Long createdBy
) {

    public static SearchDeliveryDetailResponse from(SearchDeliveryDetailResult result) {
        return new SearchDeliveryDetailResponse(
                result.deliveryId(),
                result.orderId(),
                result.userId(),
                result.status(),
                result.departureHubId(),
                result.arrivalHubId(),
                result.deliveryAddress(),
                result.receiverName(),
                result.receiverSlackId(),
                result.companyAgentId(),
                result.createdAt(),
                result.createdBy()
        );
    }
}