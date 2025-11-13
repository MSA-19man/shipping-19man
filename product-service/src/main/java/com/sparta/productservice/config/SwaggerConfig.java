package com.sparta.productservice.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI productServiceAPI() {
		return new OpenAPI()
			.info(new Info()
				.title("Product Service API")
				.description("상품 서비스 API")
				.version("v1.0.0")
			)
			.servers(List.of(
				new Server()
					.url("http://localhost:19091")
					.description("Gateway를 통한 접근"),
				new Server()
					.url("http://localhost:19500")
					.description("로컬 개발 서버")
			));
	}
}
