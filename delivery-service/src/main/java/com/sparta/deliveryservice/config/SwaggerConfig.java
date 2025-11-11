package com.sparta.deliveryservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI deliveryServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Delivery Service API")
                        .description("배송 서비스 API")
                        .version("v1.0.0")
                )
                .servers(List.of(
                        new Server()
                                .url("http://localhost:19091")
                                .description("Gateway를 통한 접근"),
                        new Server()
                                .url("http://localhost:19700")
                                .description("로컬 개발 서버")
                ));
    }
}
