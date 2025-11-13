package com.sparta.common.security;

import java.util.Enumeration;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UserContextInterceptor implements HandlerInterceptor {

	private static final String HEADER_USER_ID = "X-USER-ID";
	private static final String HEADER_USER_ROLE = "X-USER-ROLE";

	@Override
	public boolean preHandle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler
	) {
		String userId = request.getHeader(HEADER_USER_ID);
		String role = request.getHeader(HEADER_USER_ROLE);

		// if (userId != null && role != null) {
		// 	UserPrincipal userPrincipal = UserPrincipal.builder()
		// 		.userId(Long.parseLong(userId))
		// 		.role(role)
		// 		.build();
		//
		// 	UserContextHolder.setUser(userPrincipal);
		//
		// 	log.debug("User context set - userId: {}, role: {}", userId, role);
		// }

		if (userId != null && role != null) {
			try {
				UserPrincipal userPrincipal = UserPrincipal.builder()
					.userId(Long.parseLong(userId))
					.role(role)
					.build();

				UserContextHolder.setUser(userPrincipal);
				log.debug("User context set - userId: {}, role: {}", userId, role);

			} catch (NumberFormatException e) {
				log.warn("Failed to parse X-USER-ID header: {}", userId, e);
			}
		} else {
			log.warn("User headers (X-USER-ID or X-USER-ROLE) not found.");
			log.warn("Dumping all headers for URI: {}", request.getRequestURI());
			Enumeration<String> headerNames = request.getHeaderNames();
			if (headerNames != null) {
				while (headerNames.hasMoreElements()) {
					String headerName = headerNames.nextElement();
					log.warn("Header -> {} : {}", headerName, request.getHeader(headerName));
				}
			}
		}

		return true;
	}

	@Override
	public void afterCompletion(
		HttpServletRequest request,
		HttpServletResponse response,
		Object handler,
		Exception ex
	) throws Exception {
		UserContextHolder.clear();
		log.debug("User context cleared");
	}
}
