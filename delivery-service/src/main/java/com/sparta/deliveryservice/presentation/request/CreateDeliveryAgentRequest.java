package com.sparta.deliveryservice.presentation.request;

import com.sparta.deliveryservice.application.dto.CreateDeliveryAgentCommand;

import java.util.UUID;

public record CreateDeliveryAgentRequest(
        Long userId,
        UUID hubId
) {
    public CreateDeliveryAgentCommand toCommand() {
        return new CreateDeliveryAgentCommand(
                this.userId,
                this.hubId
        );
    }
}
