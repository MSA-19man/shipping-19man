package com.sparta.common.security;

public class UserContextHolder {
	public static final ThreadLocal<UserPrincipal> userContext = new ThreadLocal<>();

	public static void setUser(UserPrincipal user) {
		userContext.set(user);
	}

	/**
	 * 현재 스레드의 사용자 정보 조회
	 */
	public static UserPrincipal getUser() {
		return userContext.get();
	}

	/**
	 * 현재 스레드의 사용자 정보 제거 (메모리 누수 방지)
	 */
	public static void clear() {
		userContext.remove();
	}

	/**
	 * 사용자 정보 존재 여부 확인
	 */
	public static boolean hasUser() {
		return userContext.get() != null;
	}
}
