package com.sparta.deliveryservice.application.dto;

import java.util.UUID;

public record CreateDeliveryCommand(
        UUID orderId,
        UUID departureHubId,
        UUID arrivalHubId,
        String deliveryAddress,
        String receiverName,
        String receiverSlackId,
        UUID companyAgentId
) {
}
