package com.sparta.deliveryservice.presentation.request;

import com.sparta.deliveryservice.application.dto.UpdateDeliveryCommand;

public record UpdateDeliveryRequest(
        String deliveryAddress,
        String receiverName,
        String receiverSlackId
) {
    public UpdateDeliveryCommand toCommand() {
        return new UpdateDeliveryCommand(
                deliveryAddress,
                receiverName,
                receiverSlackId
        );
    }
}
