package com.sparta.gatewayservice.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "gateway.auth")
public class GatewayConfigProperty {

	private List<String> excludedPaths;
}
