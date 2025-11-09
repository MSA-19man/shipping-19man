package com.sparta.hubservice.presentation.response;

import com.sparta.hubservice.application.dto.FindHubResult;

import java.util.UUID;

public record FindHubResponse(
        UUID hubId,
        String name,
        String address,
        Double latitude,
        Double longitude,
        String status
) {

    public static FindHubResponse from(FindHubResult result){
        return new FindHubResponse(
                result.hubId(),
                result.name(),
                result.address(),
                result.latitude(),
                result.longitude(),
                result.status().name()
        );
    }
}
