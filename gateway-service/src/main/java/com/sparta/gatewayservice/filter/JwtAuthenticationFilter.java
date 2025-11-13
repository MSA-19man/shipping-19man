package com.sparta.gatewayservice.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;

import com.sparta.gatewayservice.config.GatewayConfigProperty;
import com.sparta.gatewayservice.jwt.JwtValidProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter implements GlobalFilter {

	private final JwtValidProvider jwtValidProvider;
	private final AntPathMatcher pathMatcher = new AntPathMatcher();
	private final GatewayConfigProperty gatewayConfigProperty;

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		ServerHttpRequest request = exchange.getRequest();
		String path = request.getURI().getPath();

		boolean isExcluded = gatewayConfigProperty.getExcludedPaths()
			.stream()
			.anyMatch(pattern -> pathMatcher.match(pattern, path));

		if (isExcluded) {
			return chain.filter(exchange);
		}

		String token = jwtValidProvider.resolveToken(request);

		if (token == null || token.isEmpty()) {
			log.warn("JWT Token is missing in request header.");
			return onError(exchange, "Token is missing", HttpStatus.UNAUTHORIZED);
		}

		if (!jwtValidProvider.validateToken(token)) {
			log.warn("Invalid JWT Token.");
			return onError(exchange, "Token not Valid", HttpStatus.UNAUTHORIZED);
		}

		Long userId = jwtValidProvider.getUserId(token);
		String role = jwtValidProvider.getRole(token);

		ServerHttpRequest newRequest = request.mutate()
			.header("X-USER-ID", userId.toString())
			.header("X-USER-ROLE", role)
			.build();

		return chain.filter(exchange.mutate().request(newRequest).build());
	}

	private Mono<Void> onError(ServerWebExchange exchange, String errMessage, HttpStatus httpStatus) {
		ServerHttpResponse response = exchange.getResponse();
		response.setStatusCode(httpStatus);
		log.error(errMessage);

		return response.setComplete();
	}

	public int getOrder() {
		return -1;
	}
}
