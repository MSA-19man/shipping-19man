package com.sparta.deliveryservice.presentation.controller;

import com.sparta.common.response.ApiResponse;
import com.sparta.common.response.PageResponse;
import com.sparta.deliveryservice.application.dto.SearchDeliveryRouteResult;
import com.sparta.deliveryservice.application.service.DeliveryRouteService;
import com.sparta.deliveryservice.presentation.response.SearchDeliveryRouteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/api/v1/deliveries")
@RequiredArgsConstructor
@Tag(name = "배송 경로 API", description = "배송 경로 관련 기능 API입니다.")
public class DeliveryRouteController {

    private final DeliveryRouteService deliveryRouteService;

    @Operation(summary = "배송 경로 조회", description = "배송 경로를 모두 조회합니다.")
    @GetMapping("/{deliveryId}/routes")
    public ResponseEntity<ApiResponse<PageResponse<SearchDeliveryRouteResponse>>> searchDeliveryRoute(
            @PathVariable UUID deliveryId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort
    ){
        Sort.Direction direction = sort.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Page<SearchDeliveryRouteResult> resultPage = deliveryRouteService.searchDeliveryRouteResult(deliveryId,page,size,direction);
        Page<SearchDeliveryRouteResponse>  responsePage = resultPage.map(SearchDeliveryRouteResponse::from);
        PageResponse<SearchDeliveryRouteResponse> pageResponse = PageResponse.of(responsePage);
        return ResponseEntity.ok()
                .body(ApiResponse.success(pageResponse, "배송 경로 기록 조회가 완료되었습니다."));

    }
}
