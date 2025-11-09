package com.sparta.deliveryservice.presentation.response;

import com.sparta.deliveryservice.application.dto.CreateDeliveryResult;
import com.sparta.deliveryservice.domain.model.DeliveryStatus;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateDeliveryResponse(
        UUID deliveryId,
        UUID orderId,
        DeliveryStatus status,
        UUID departureHubId,
        UUID arrivalHubId,
        String deliveryAddress,
        String receiverName,
        String receiverSlackId,
        UUID companyAgentId
) {
    public static CreateDeliveryResponse from(CreateDeliveryResult result) {
        return CreateDeliveryResponse.builder()
                .deliveryId(result.deliveryId())
                .orderId(result.orderId())
                .status(result.status())
                .departureHubId(result.departureHubId())
                .arrivalHubId(result.arrivalHubId())
                .deliveryAddress(result.deliveryAddress())
                .receiverName(result.receiverName())
                .receiverSlackId(result.receiverSlackId())
                .companyAgentId(result.companyAgentId())
                .build();
    }
}
