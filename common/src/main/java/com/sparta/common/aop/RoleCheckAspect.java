package com.sparta.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.sparta.common.security.UserContextHolder;
import com.sparta.common.security.UserPrincipal;

@Aspect
@Component
public class RoleCheckAspect {

	@Before("@annotation(com.sparta.common.aop.RoleCheck) || " +
		"@within(com.sparta.common.aop.RoleCheck)")
	public void checkRole(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature)joinPoint.getSignature();

		RoleCheck methodAnnotation = signature.getMethod().getAnnotation(RoleCheck.class);

		RoleCheck classAnotation = joinPoint.getTarget().getClass().getAnnotation(RoleCheck.class);

		RoleCheck roleCheck = methodAnnotation != null ? methodAnnotation : classAnotation;

		if (roleCheck == null) {
			return;
		}

		UserPrincipal user = UserContextHolder.getUser();
		if (user == null) {
			throw new RuntimeException("인증이 필요한 요청입니다.");
		}

		String[] roleChecks = roleCheck.value();
		if (!user.hasAnyRole(roleChecks)) {
			throw new RuntimeException("접근 권한이 없습니다.");
		}
	}
}
