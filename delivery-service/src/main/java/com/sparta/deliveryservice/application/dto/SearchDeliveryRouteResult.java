package com.sparta.deliveryservice.application.dto;

import com.sparta.deliveryservice.domain.model.DeliveryRoute;
import com.sparta.deliveryservice.domain.model.DeliveryRouteStatus;

import java.util.UUID;

public record SearchDeliveryRouteResult(
        UUID id,
        UUID deliveryId,
        UUID departureHubId,
        UUID arrivalHubId,
        Integer sequence,
        DeliveryRouteStatus status,
        Double actualDistance,
        Integer actualDuration
) {
    public static SearchDeliveryRouteResult from(DeliveryRoute route){
        return new SearchDeliveryRouteResult(
                route.getId(),
                route.getDelivery().getId(),
                route.getDepartureHubId(),
                route.getArrivalHubId(),
                route.getSequence(),
                route.getStatus(),
                route.getActualDistance(),
                route.getActualDuration()
        );
    }
}
