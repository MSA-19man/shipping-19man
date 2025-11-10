package com.sparta.deliveryservice.application.dto;

import com.sparta.deliveryservice.domain.model.Delivery;
import com.sparta.deliveryservice.domain.model.DeliveryStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record SearchDeliveryDetailResult(
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
    public static SearchDeliveryDetailResult from(Delivery delivery) {
        return new SearchDeliveryDetailResult(
                delivery.getId(),
                delivery.getOrderId(),
                delivery.getUserId(),
                delivery.getStatus(),
                delivery.getDepartureHubId(),
                delivery.getArrivalHubId(),
                delivery.getDeliveryAddress(),
                delivery.getReceiverName(),
                delivery.getReceiverSlackId(),
                delivery.getCompanyAgentId(),
                delivery.getCreatedAt(),
                delivery.getCreatedBy()
        );
    }
}
