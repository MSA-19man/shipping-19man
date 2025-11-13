package com.sparta.deliveryservice.presentation.controller;

import com.sparta.common.response.ApiResponse;
import com.sparta.deliveryservice.application.dto.CreateDeliveryAgentCommand;
import com.sparta.deliveryservice.application.dto.CreateDeliveryAgentResult;
import com.sparta.deliveryservice.application.service.DeliveryAgentService;
import com.sparta.deliveryservice.presentation.request.CreateDeliveryAgentRequest;
import com.sparta.deliveryservice.presentation.response.CreateDeliveryAgentResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/deliveries/agents")
@RequiredArgsConstructor
@Tag(name = "배송 담당자 API", description = "배송 담당자 관련 기능 API입니다.")
public class DeliveryAgentController {

    private final DeliveryAgentService deliveryAgentService;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateDeliveryAgentResponse>> createAgent(
            @RequestBody CreateDeliveryAgentRequest request
    ){

        CreateDeliveryAgentCommand command = request.toCommand();
        CreateDeliveryAgentResult result = deliveryAgentService.createDeliveryAgent(command);
        CreateDeliveryAgentResponse response = CreateDeliveryAgentResponse.from(result);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(response,"배송 담당자가 생성되었습니다."));
    }
}
