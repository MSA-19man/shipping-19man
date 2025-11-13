package com.sparta.common.security;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private final UserContextInterceptor userContextInterceptor;
	private final CurrentUserArgumentResolver currentUserArgumentResolver;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userContextInterceptor)
			.addPathPatterns("/**")
			.excludePathPatterns(
				"/api/v1/users/signup",
				"/api/v1/auth/login",
				"/actuator/**",
				"/swagger-ui/**",
				"/v3/api-docs/**"
			);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(currentUserArgumentResolver);
	}
}
