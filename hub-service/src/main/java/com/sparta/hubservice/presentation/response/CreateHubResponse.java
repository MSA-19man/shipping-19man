package com.sparta.hubservice.presentation.response;

import com.sparta.hubservice.application.dto.CreateHubResult;

import java.util.UUID;

public record CreateHubResponse(
        UUID hubId,
        String name,
        String address,
        Double latitude,
        Double longitude,
        String status
) {
    // Service가 반환한 dtoa를 Controller의 response Dto로 변환
    public static CreateHubResponse from(CreateHubResult result){
        return new CreateHubResponse(
                result.hubId(),
                result.name(),
                result.address(),
                result.latitude(),
                result.longitude(),
                result.status().name() //string으로
        );
    }
}
