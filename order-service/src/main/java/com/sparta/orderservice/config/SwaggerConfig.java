package com.sparta.orderservice.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI orderServiceAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("Order Service API")
				.description("주문 서비스 API")
				.version("v1.0.0")
			)
			.servers(List.of(
				new Server()
					.url("http://localhost:19091")
					.description("Gateway를 통한 접근"),
				new Server()
					.url("http://localhost:19600")
					.description("로컬 개발 서버")
			));
	}
}
