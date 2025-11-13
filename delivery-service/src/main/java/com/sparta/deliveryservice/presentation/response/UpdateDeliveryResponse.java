package com.sparta.deliveryservice.presentation.response;

import com.sparta.deliveryservice.application.dto.UpdateDeliveryResult;
import com.sparta.deliveryservice.domain.model.DeliveryStatus;

import java.util.UUID;

public record UpdateDeliveryResponse(
        UUID deliveryId,
        UUID orderId,
        DeliveryStatus status,
        String deliveryAddress,
        String receiverCompany
) {
    public static UpdateDeliveryResponse from(UpdateDeliveryResult result){
        return new UpdateDeliveryResponse(
                result.deliveryId(),
                result.orderId(),
                result.status(),
                result.deliveryAddress(),
                result.receiverCompany()
        );
    }
}
