package com.sparta.deliveryservice.application.dto;

import com.sparta.deliveryservice.domain.model.DeliveryAgent;

import java.util.UUID;

public record CreateDeliveryAgentCommand(
        Long userId,
        UUID hubId
) {
    public DeliveryAgent toEntity(Integer emptyIndex) {
        if (hubId != null){
            return DeliveryAgent.createCompanyAgent(
                    userId,
                    hubId,
                    emptyIndex
            );
        }
        return DeliveryAgent.createHubAgent(
                userId,
                emptyIndex
        );
    }
}
