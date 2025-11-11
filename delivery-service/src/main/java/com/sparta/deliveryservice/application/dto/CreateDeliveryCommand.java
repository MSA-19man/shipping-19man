package com.sparta.deliveryservice.application.dto;

import com.sparta.deliveryservice.domain.model.Delivery;

import java.util.UUID;

public record CreateDeliveryCommand(
        UUID orderId,
        Long userId,
        UUID departureHubId,
        UUID arrivalHubId,
        String deliveryAddress,
        String receiverName,
        String receiverSlackId,
        UUID companyAgentId
) {
    public Delivery toEntity(){
        return Delivery.of(
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
