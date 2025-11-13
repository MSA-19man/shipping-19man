package com.sparta.common.security;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(CurrentUser.class) &&
			parameter.getParameterType().equals(UserPrincipal.class);
	}

	@Override
	public Object resolveArgument(
		MethodParameter parameter,
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory
	) throws Exception {
		UserPrincipal user = UserContextHolder.getUser();

		CurrentUser annotation = parameter.getParameterAnnotation(CurrentUser.class);
		if (annotation != null && annotation.required() && user == null) {
			throw new Exception("인증이 필요한 요청입니다.");
		}
		return user;
	}
}
