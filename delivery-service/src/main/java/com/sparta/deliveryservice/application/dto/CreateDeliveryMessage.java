package com.sparta.deliveryservice.application.dto;

import java.util.UUID;

public record CreateDeliveryMessage(
        UUID orderId,
        Long userId,
        UUID departureHubId,
        UUID arrivalHubId,
        String deliveryAddress,
        String receiverCompany
) {
    public CreateDeliveryCommand toCommand() {
        return new CreateDeliveryCommand(
                orderId, userId, departureHubId,
                arrivalHubId, deliveryAddress, receiverCompany
        );
    }
}
