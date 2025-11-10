package com.sparta.hubservice.application.dto;

import com.sparta.hubservice.domain.model.Hub;
import com.sparta.hubservice.domain.model.HubStatus;

import java.util.UUID;

public record FindHubResult(
        UUID hubId,
        String name,
        String address,
        Double latitude,
        Double longitude,
        HubStatus status
) {
    // 허브 엔티티를 서비스 dto로
    public static FindHubResult from(Hub hub){
        return new FindHubResult(
                hub.getId(),
                hub.getName(),
                hub.getAddress(),
                hub.getLatitude(),
                hub.getLongitude(),
                hub.getStatus()
        );
    }
}
