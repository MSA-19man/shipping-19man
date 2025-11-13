package com.sparta.deliveryservice.infrastructure.client;

import com.sparta.deliveryservice.infrastructure.client.dto.DeliveryRouteCalcResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "hub-service",path = "/internal/v1/hub-routes")
public interface HubFeignClient {

    @GetMapping("/search")
    DeliveryRouteCalcResult findPath(
            @RequestParam("start")UUID start,
            @RequestParam("end") UUID end
            );
}
