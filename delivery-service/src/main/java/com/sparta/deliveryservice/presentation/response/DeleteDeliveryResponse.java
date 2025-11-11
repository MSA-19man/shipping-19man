package com.sparta.deliveryservice.presentation.response;

import com.sparta.deliveryservice.application.dto.DeleteDeliveryResult;

import java.time.LocalDateTime;
import java.util.UUID;

public record DeleteDeliveryResponse(
        UUID deliveryId,
        LocalDateTime deletedAt,
        Long deletedBy
) {
    public static DeleteDeliveryResponse from(DeleteDeliveryResult result){
        return new DeleteDeliveryResponse(
                result.deliveryId(),
                result.deletedAt(),
                result.deletedBy()
        );
    }
}
