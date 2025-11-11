package com.sparta.deliveryservice.application.dto;

import com.sparta.deliveryservice.domain.model.Delivery;

import java.time.LocalDateTime;
import java.util.UUID;

public record DeleteDeliveryResult(
        UUID deliveryId,
        LocalDateTime deletedAt,
        Long deletedBy
) {
    public static DeleteDeliveryResult from(Delivery delivery){
        return new DeleteDeliveryResult(
                delivery.getId(),
                delivery.getDeletedAt(),
                delivery.getDeletedBy()
        );
    }
}
