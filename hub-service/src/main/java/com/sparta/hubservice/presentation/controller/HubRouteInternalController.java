package com.sparta.hubservice.presentation.controller;

import com.sparta.hubservice.application.dto.DijkstraRouteResult;
import com.sparta.hubservice.application.service.DijkstraService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/v1/hub-routes")
public class HubRouteInternalController {

    private final DijkstraService dijkstraService;

    @GetMapping("/search")
    public DijkstraRouteResult findPath(
            @RequestParam(name = "start") UUID start,
            @RequestParam(name = "end") UUID end
    ){

        return dijkstraService.findPath(start, end);
    }
}
