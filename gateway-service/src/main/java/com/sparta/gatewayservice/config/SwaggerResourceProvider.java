package com.sparta.gatewayservice.config;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.stereotype.Component;

@Component
public class SwaggerResourceProvider {

	private final RouteDefinitionLocator routeDefinitionLocator;

	public SwaggerResourceProvider(RouteDefinitionLocator routeDefinitionLocator) {
		this.routeDefinitionLocator = routeDefinitionLocator;
	}

	public List<SwaggerUiConfigProperties.SwaggerUrl> getSwaggerUrls() {
		List<SwaggerUiConfigProperties.SwaggerUrl> urls = new ArrayList<>();

		// Delivery Service
		urls.add(createSwaggerUrl("delivery-service", "/delivery-service/api-docs"));

		// Company Service
		urls.add(createSwaggerUrl("company-service", "/company-service/api-docs"));

		// Hub Service
		urls.add(createSwaggerUrl("hub-service", "/hub-service/api-docs"));

		// Order Service
		urls.add(createSwaggerUrl("order-service", "/order-service/api-docs"));

		// Product Service
		urls.add(createSwaggerUrl("product-service", "/product-service/api-docs"));

		return urls;
	}

	private SwaggerUiConfigProperties.SwaggerUrl createSwaggerUrl(String name, String url) {
		SwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new SwaggerUiConfigProperties.SwaggerUrl();
		swaggerUrl.setName(name);
		swaggerUrl.setUrl(url);
		return swaggerUrl;
	}
}