package com.sparta.hubservice.presentation.controller;

import com.sparta.common.response.ApiResponse;
import com.sparta.common.response.PageResponse;
import com.sparta.hubservice.application.dto.CreateHubCommand;
import com.sparta.hubservice.application.dto.CreateHubResult;
import com.sparta.hubservice.application.dto.FindHubResult;
import com.sparta.hubservice.application.service.HubService;
import com.sparta.hubservice.presentation.request.CreateHubRequest;
import com.sparta.hubservice.presentation.response.CreateHubResponse;
import com.sparta.hubservice.presentation.response.FindHubResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<FindHubResponse>>> getHubs(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
            ){
        Page<FindHubResult> hubPageResult = hubService.getHubs(pageable); // 전체 조회 요청

        // Service의 Page<FindHubResult>를 Response의 Page<FindHubResponse>로 변환
        Page<FindHubResponse> hubPageResponse = hubPageResult.map(FindHubResponse::from);

        PageResponse<FindHubResponse> responseData = PageResponse.of(hubPageResponse);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(responseData));
    }

    @GetMapping("/{hubId}")
    public ResponseEntity<ApiResponse<FindHubResponse>> getHubById(
            @PathVariable UUID hubId
    ){
        // 서비스 호출
        //dto 변환
        //ApiResponse로 변환해서 최종 반환
        FindHubResult result = hubService.getHubById(hubId);

        FindHubResponse responseData = FindHubResponse.from(result);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApiResponse.success(responseData));
    }
}
