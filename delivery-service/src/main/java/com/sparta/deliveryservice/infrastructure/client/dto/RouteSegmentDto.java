package com.sparta.deliveryservice.infrastructure.client.dto;

import java.util.UUID;

public record RouteSegmentDto(
        UUID departureHubId,
        UUID arrivalHubId,
        Integer sequence,
        Double distance,
        Integer timeInMinutes
) {
}
