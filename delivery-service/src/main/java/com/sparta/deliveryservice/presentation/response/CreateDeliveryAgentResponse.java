package com.sparta.deliveryservice.presentation.response;

import com.sparta.deliveryservice.application.dto.CreateDeliveryAgentResult;
import com.sparta.deliveryservice.domain.model.DeliveryAgentType;

import java.util.UUID;

public record CreateDeliveryAgentResponse(
        UUID id,
        Long userId,
        UUID hubId,
        Integer deliveryIndex,
        DeliveryAgentType agentType
) {
    public static CreateDeliveryAgentResponse from(CreateDeliveryAgentResult result) {
        return new CreateDeliveryAgentResponse(
                result.id(),
                result.userId(),
                result.hubId(),
                result.deliveryIndex(),
                result.agentType()
        );
    }
}
