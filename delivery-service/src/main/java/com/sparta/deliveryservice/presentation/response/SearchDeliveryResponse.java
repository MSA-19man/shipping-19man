package com.sparta.deliveryservice.presentation.response;

import com.sparta.deliveryservice.application.dto.SearchDeliveryResult;
import com.sparta.deliveryservice.domain.model.DeliveryStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record SearchDeliveryResponse(
        UUID deliveryId,
        UUID orderId,
        Long userId,
        DeliveryStatus status,
        UUID departureHubId,
        UUID arrivalHubId,
        String deliveryAddress,
        String receiverName,
        UUID companyAgentId,
        LocalDateTime createdAt,
        Long createdBy
) {

    public static SearchDeliveryResponse from(SearchDeliveryResult result){
        return new SearchDeliveryResponse(
                result.deliveryId(),
                result.orderId(),
                result.userId(),
                result.status(),
                result.departureHubId(),
                result.arrivalHubId(),
                result.deliveryAddress(),
                result.receiverCompany(),
                result.companyAgentId(),
                result.createdAt(),
                result.createdBy()
        );
    }
}
