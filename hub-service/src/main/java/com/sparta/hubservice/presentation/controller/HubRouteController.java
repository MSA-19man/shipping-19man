package com.sparta.hubservice.presentation.controller;

import com.sparta.common.response.ApiResponse;
import com.sparta.hubservice.application.dto.DijkstraRouteResult;
import com.sparta.hubservice.application.service.DijkstraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/hub-routes")
public class HubRouteController {

    private final DijkstraService dijkstraService;

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<DijkstraRouteResult>> findPath(
            @RequestParam UUID start,
            @RequestParam UUID end
    ){
        DijkstraRouteResult result = dijkstraService.findPath(start, end);

        return ResponseEntity.ok(ApiResponse.success(result));
    }
}
