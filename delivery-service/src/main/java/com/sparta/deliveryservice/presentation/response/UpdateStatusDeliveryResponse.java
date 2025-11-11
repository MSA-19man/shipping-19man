package com.sparta.deliveryservice.presentation.response;

import com.sparta.deliveryservice.application.dto.UpdateStatusDeliveryResult;
import com.sparta.deliveryservice.domain.model.DeliveryStatus;

import java.util.UUID;

public record UpdateStatusDeliveryResponse(
        UUID deliveryId,
        DeliveryStatus beforeStatus,
        DeliveryStatus currentStatus
) {
    public static UpdateStatusDeliveryResponse from(UpdateStatusDeliveryResult result){
        return new UpdateStatusDeliveryResponse(
                result.deliveryId(),
                result.beforeStatus(),
                result.currentStatus()
        );
    }
}
