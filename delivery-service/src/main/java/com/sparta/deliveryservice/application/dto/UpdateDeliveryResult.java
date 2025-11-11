package com.sparta.deliveryservice.application.dto;

import com.sparta.deliveryservice.domain.model.Delivery;
import com.sparta.deliveryservice.domain.model.DeliveryStatus;

import java.util.UUID;

public record UpdateDeliveryResult(
        UUID deliveryId,
        UUID orderId,
        DeliveryStatus status,
        String deliveryAddress,
        String receiverName,
        String receiverSlackId
) {
    public static UpdateDeliveryResult from(Delivery delivery){
        return new UpdateDeliveryResult(
                delivery.getId(),
                delivery.getOrderId(),
                delivery.getStatus(),
                delivery.getDeliveryAddress(),
                delivery.getReceiverName(),
                delivery.getReceiverSlackId()
        );
    }
}
