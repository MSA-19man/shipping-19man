package com.sparta.userservice.user.infra.client;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "hub-service")
public interface HubClient {

	@GetMapping("/internal/v1/hubs/{hubId}")
	Boolean existByHubId(@PathVariable("hubId") UUID hubId);
}
