package com.sparta.deliveryservice.presentation.request;

import com.sparta.deliveryservice.application.dto.CreateDeliveryCommand;

import java.util.UUID;

public record CreateDeliveryRequest(
        UUID orderId,
        Long userId,
        UUID departureHubId,
        UUID arrivalHubId,
        String deliveryAddress,
        String receiverName,
        String receiverSlackId,
        UUID companyAgentId
) {
    public CreateDeliveryCommand toCommand() {
        return new CreateDeliveryCommand(
                this.orderId,
                this.userId,
                this.departureHubId,
                this.arrivalHubId,
                this.deliveryAddress,
                this.receiverName,
                this.receiverSlackId,
                this.companyAgentId
        );
    }
}
