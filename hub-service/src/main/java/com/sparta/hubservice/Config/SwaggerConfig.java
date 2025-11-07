package com.sparta.hubservice.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server; // ⬅️ Server 임포트
import org.springframework.beans.factory.annotation.Value; // ⬅️ @Value 임포트
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List; // ⬅️ List 임포트

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi(
            // 1. .yml 파일에서 "openapi.service.url" 값을 읽어옵니다.
            @Value("${openapi.service.url}") String gatewayUrl
    ) {
        return new OpenAPI()
                // 2. (중요) 스웨거 UI의 "서버 주소"를 게이트웨이로 설정
                .servers(List.of(new Server().url(gatewayUrl)))

                // 3. API 문서 정보 설정
                .info(new Info().title("Hub Service API")
                        .version("0.0.1"));
    }
}