package com.sparta.deliveryservice.presentation.request;

import com.sparta.deliveryservice.application.dto.UpdateDeliveryCommand;

public record UpdateDeliveryRequest(
        String deliveryAddress,
        String receiverCompany
) {
    public UpdateDeliveryCommand toCommand() {
        return new UpdateDeliveryCommand(
                deliveryAddress,
                receiverCompany
        );
    }
}
