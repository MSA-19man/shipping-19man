package com.sparta.deliveryservice.presentation.response;

import com.sparta.deliveryservice.application.dto.SearchDeliveryRouteResult;
import com.sparta.deliveryservice.domain.model.DeliveryRouteStatus;

import java.util.UUID;

public record SearchDeliveryRouteResponse(
        UUID id,
        UUID deliveryId,
        UUID departureHubId,
        UUID arrivalHubId,
        Integer sequence,
        DeliveryRouteStatus status,
        Double actualDistance,
        Integer  actualDuration
) {
    public static SearchDeliveryRouteResponse from(SearchDeliveryRouteResult result){
        return new SearchDeliveryRouteResponse(
                result.id(),
                result.deliveryId(),
                result.departureHubId(),
                result.arrivalHubId(),
                result.sequence(),
                result.status(),
                result.actualDistance(),
                result.actualDuration()
        );
    }
}
