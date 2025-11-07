package com.sparta.deliveryservice.presentation.controller;

import com.sparta.common.response.ApiResponse;
import com.sparta.deliveryservice.application.dto.CreateDeliveryCommand;
import com.sparta.deliveryservice.application.dto.CreateDeliveryResult;
import com.sparta.deliveryservice.application.service.DeliveryService;
import com.sparta.deliveryservice.presentation.request.CreateDeliveryRequest;
import com.sparta.deliveryservice.presentation.response.CreateDeliveryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delivery")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateDeliveryResponse>> createDelivery(
            @RequestBody CreateDeliveryRequest request) {

        CreateDeliveryCommand command = request.toCommand();

        CreateDeliveryResult result = deliveryService.createDelivery(command);

        CreateDeliveryResponse response = CreateDeliveryResponse.from(result);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "배송이 생성되었습니다."));
    }
}
