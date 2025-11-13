package com.sparta.hubservice.presentation.controller;

import com.sparta.hubservice.application.service.HubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/internal/v1/hubs")
@RequiredArgsConstructor
public class HubInternalController {
	private final HubService hubService;

	@GetMapping("/{hubId}/exists")
	public Boolean existsHub(@PathVariable("hubId") UUID hubId) {
		return hubService.existHub(hubId);
	}
}
