package com.sparta.hubservice.presentation.request;

import com.sparta.hubservice.application.dto.CreateHubCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CreateHubRequest(
        @NotBlank(message = "허브 이름은 필수입니다.")
        String name,
        @NotBlank(message = "주소는 필수입니다.")
        String address,
        @NotNull(message = "위도는 필수입니다.")
        Double latitude,
        @NotNull(message = "경도는 필수입니다.")
        Double longitude
) {
    //RequestDto를 Service가 이해하는 CommandDto로 변환해줌
    public CreateHubCommand toCommand(){
        return new CreateHubCommand(
                this.name,
                this.address,
                this.latitude,
                this.longitude
        );
    }
}
