package com.sparta.userservice.user.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApprovalStatus {
	APPROVED("승인됨"),
	PENDING("승인 대기중"),
	REJECTED("거절됨");

	private final String description;
}
