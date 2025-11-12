package com.sparta.hubservice.application.dto;

import java.util.List;

public record DijkstraRouteResult(
        List<RouteSegmentDto> segments,
        double totalDistance,
        int totalTime
) {
}
