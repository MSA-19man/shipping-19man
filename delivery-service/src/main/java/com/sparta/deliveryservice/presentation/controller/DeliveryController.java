package com.sparta.deliveryservice.presentation.controller;

import com.sparta.common.response.ApiResponse;
import com.sparta.common.response.PageResponse;
import com.sparta.deliveryservice.application.dto.*;
import com.sparta.deliveryservice.application.service.DeliveryService;
import com.sparta.deliveryservice.presentation.request.CreateDeliveryRequest;
import com.sparta.deliveryservice.presentation.request.UpdateDeliveryRequest;
import com.sparta.deliveryservice.presentation.response.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
@Tag(name = "배송 API", description = "배송 관련 기능 API입니다.")
public class DeliveryController {

    private final DeliveryService deliveryService;

    @Operation(summary = "배송 생성", description = "주문이 생성된 경우 배송을 생성합니다.")
    @PostMapping
    public ResponseEntity<ApiResponse<CreateDeliveryResponse>> createDelivery(
            @RequestBody CreateDeliveryRequest request) {

        CreateDeliveryCommand command = request.toCommand();

        CreateDeliveryResult result = deliveryService.createDelivery(command);

        CreateDeliveryResponse response = CreateDeliveryResponse.from(result);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(response, "배송이 생성되었습니다."));
    }

    @Operation(summary = "배송 상세 조회", description = "배송을 상세 조회합니다.")
    @GetMapping("/{deliveryId}")
    public ResponseEntity<ApiResponse<SearchDeliveryDetailResponse>> searchDeliveryDetail(
            @PathVariable UUID deliveryId
    ) {

        SearchDeliveryDetailResult result = deliveryService.searchDeliveryDetail(deliveryId);

        SearchDeliveryDetailResponse response = SearchDeliveryDetailResponse.from(result);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(response, "배송 상세 조회가 완료되었습니다."));
    }

    @Operation(summary = "권한별 배송 다건 조회", description = "권한별 배송을 모두 조회합니다.")
    @GetMapping()
    public ResponseEntity<ApiResponse<PageResponse<SearchDeliveryResponse>>> searchDelivery(
            @RequestParam(required = false) Long tempUserId,        // 임시
            @RequestParam(required = false) String tempRole,        // 임시
            @RequestParam(required = false) UUID tempHubId,         // 임시
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort
    ) {
        Sort.Direction direction = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;

        Page<SearchDeliveryResult> resultPage = deliveryService.searchDelivery(tempUserId, tempRole, tempHubId, page, size, direction);

        Page<SearchDeliveryResponse> responsePage = resultPage.map(SearchDeliveryResponse::from);
        PageResponse<SearchDeliveryResponse> pageResponse = PageResponse.of(responsePage);
        return ResponseEntity.ok()
                .body(ApiResponse.success(pageResponse, "배송 목록 조회가 완료되었습니다."));
    }

    @Operation(summary = "배송 상태 변경", description = "배송 상태를 다음 단계로 자동 변경합니다. " +
            "HUB_WAITING → HUB_MOVING → HUB_ARRIVED → COMPANY_MOVING → COMPLETED")
    @PatchMapping("/{deliveryId}")
    public ResponseEntity<ApiResponse<UpdateStatusDeliveryResponse>> updateStatusDelivery(
            @PathVariable UUID deliveryId
    ) {
        UpdateStatusDeliveryResult result = deliveryService.UpdateStatusDelivery(deliveryId);
        UpdateStatusDeliveryResponse response = UpdateStatusDeliveryResponse.from(result);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(response, "배송 상태가 변경되었습니다."));
    }

    @Operation(summary = "배송 삭제", description = "배송을 삭제하는 API 입니다.")
    @DeleteMapping("/{deliveryId}")
    public ResponseEntity<ApiResponse<DeleteDeliveryResponse>> deleteDelivery(
            @PathVariable UUID deliveryId
    ) {
        DeleteDeliveryResult result = deliveryService.DeleteDelivery(deliveryId);
        DeleteDeliveryResponse response = DeleteDeliveryResponse.from(result);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(response,"배송이 삭제되었습니다."));
    }

    @Operation(summary = "배송 수정", description = "배송을 수정하는 API 입니다.")
    @PutMapping("/{deliveryId}")
    public ResponseEntity<ApiResponse<UpdateDeliveryResponse>> updateDelivery(
            @PathVariable UUID deliveryId,
            @RequestBody UpdateDeliveryRequest request
    ) {
        UpdateDeliveryCommand command = request.toCommand();
        UpdateDeliveryResult result = deliveryService.UpdateDelivery(command,deliveryId);
        UpdateDeliveryResponse response = UpdateDeliveryResponse.from(result);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(response,"배송이 수정되었습니다."));
    }
}
