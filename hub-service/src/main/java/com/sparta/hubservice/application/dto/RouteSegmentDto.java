package com.sparta.hubservice.application.dto;

import com.sparta.hubservice.domain.model.HubRoute;

import java.util.UUID;

// 각 구간 경로
public record RouteSegmentDto(
        UUID departureHubId,
        String departureHubName,
        UUID arrivalHubId,
        String arrivalHubName,
        double distance,
        int timeInMinutes
) {
    // 엔티티를 dto로
    public static RouteSegmentDto from(HubRoute segment){
        return new RouteSegmentDto(
                segment.getDepartureHub().getId(),
                segment.getDepartureHub().getName(),
                segment.getArrivalHub().getId(),
                segment.getArrivalHub().getName(),
                segment.getDistance(),
                segment.getRequiredTime()
        );
    }
}
