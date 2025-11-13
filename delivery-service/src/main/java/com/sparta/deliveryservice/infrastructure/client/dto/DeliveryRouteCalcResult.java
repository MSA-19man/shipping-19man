package com.sparta.deliveryservice.infrastructure.client.dto;

import java.util.List;

public record DeliveryRouteCalcResult(
        List<RouteSegmentDto> segments,
        double totalDistance,
        int totalTime
) {
}
