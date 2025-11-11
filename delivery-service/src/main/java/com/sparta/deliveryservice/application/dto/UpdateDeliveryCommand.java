package com.sparta.deliveryservice.application.dto;

public record UpdateDeliveryCommand(
        String deliveryAddress,
        String receiverName,
        String receiverSlackId
) {
}
