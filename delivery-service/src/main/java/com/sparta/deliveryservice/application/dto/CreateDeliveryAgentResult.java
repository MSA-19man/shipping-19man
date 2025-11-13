package com.sparta.deliveryservice.application.dto;

import com.sparta.deliveryservice.domain.model.DeliveryAgent;
import com.sparta.deliveryservice.domain.model.DeliveryAgentType;

import java.util.UUID;

public record CreateDeliveryAgentResult(
        UUID id,
        Long userId,
        UUID hubId,
        Integer deliveryIndex,
        DeliveryAgentType agentType
) {
    public static CreateDeliveryAgentResult from(DeliveryAgent agent){
        return new CreateDeliveryAgentResult(
                agent.getId(),
                agent.getUserId(),
                agent.getHubId(),
                agent.getDeliveryIndex(),
                agent.getAgentType()
        );
    }
}
