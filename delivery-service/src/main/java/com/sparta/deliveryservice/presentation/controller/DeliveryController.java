package com.sparta.deliveryservice.presentation.controller;

import com.sparta.common.response.ApiResponse;
import com.sparta.deliveryservice.application.dto.CreateDeliveryCommand;
import com.sparta.deliveryservice.application.dto.CreateDeliveryResult;
import com.sparta.deliveryservice.application.dto.SearchDeliveryDetailResult;
import com.sparta.deliveryservice.application.service.DeliveryService;
import com.sparta.deliveryservice.presentation.request.CreateDeliveryRequest;
import com.sparta.deliveryservice.presentation.response.CreateDeliveryResponse;
import com.sparta.deliveryservice.presentation.response.SearchDeliveryDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping("/{deliveryId}")
    public ResponseEntity<ApiResponse<SearchDeliveryDetailResponse>> searchDeliveryDetail(
            @PathVariable UUID deliveryId
            ){

        SearchDeliveryDetailResult result = deliveryService.searchDeliveryDetail(deliveryId);

        SearchDeliveryDetailResponse response = SearchDeliveryDetailResponse.from(result);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response, "배송 상세 조회가 완료되었습니다."));
    }
}
