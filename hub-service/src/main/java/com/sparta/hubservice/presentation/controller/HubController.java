package com.sparta.hubservice.presentation.controller;

import com.sparta.common.response.ApiResponse;
import com.sparta.hubservice.application.dto.CreateHubCommand;
import com.sparta.hubservice.application.dto.CreateHubResult;
import com.sparta.hubservice.application.service.HubService;
import com.sparta.hubservice.presentation.request.CreateHubRequest;
import com.sparta.hubservice.presentation.response.CreateHubResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/hubs")
public class HubController {

    private final HubService hubService;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateHubResponse>> createHub(
            @Valid @RequestBody CreateHubRequest request
    ){
        CreateHubCommand command = request.toCommand();
        CreateHubResult result = hubService.createHub(command);
        CreateHubResponse responseData = CreateHubResponse.from(result);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.success(responseData, "허브 생성 성공"));
    }
}
