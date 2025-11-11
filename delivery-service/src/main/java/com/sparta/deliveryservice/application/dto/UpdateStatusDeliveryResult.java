package com.sparta.deliveryservice.application.dto;

import com.sparta.deliveryservice.domain.model.Delivery;
import com.sparta.deliveryservice.domain.model.DeliveryStatus;

import java.util.UUID;

public record UpdateStatusDeliveryResult(
        UUID deliveryId,
        DeliveryStatus beforeStatus,
        DeliveryStatus currentStatus
) {
    public static UpdateStatusDeliveryResult from(
            Delivery delivery,
            DeliveryStatus beforeStatus
    ){
        return new UpdateStatusDeliveryResult(
                delivery.getId(),
                beforeStatus,
                delivery.getStatus()
        );
    }
}
