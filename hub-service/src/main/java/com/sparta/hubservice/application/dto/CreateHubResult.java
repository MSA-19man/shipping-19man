package com.sparta.hubservice.application.dto;

import com.sparta.hubservice.domain.model.Hub;
import com.sparta.hubservice.domain.model.HubStatus;

import java.util.UUID;

public record CreateHubResult(
        UUID hubId,
        String name,
        String address,
        Double latitude,
        Double longitude,
        HubStatus status
) {
    // 저장된 Hub 엔티티를 CreateHubResult Dto로 변환
    public static CreateHubResult from(Hub hub) {
        return new CreateHubResult(
                hub.getId(),
                hub.getName(),
                hub.getAddress(),
                hub.getLatitude(),
                hub.getLongitude(),
                hub.getStatus()
        );
    }
}
