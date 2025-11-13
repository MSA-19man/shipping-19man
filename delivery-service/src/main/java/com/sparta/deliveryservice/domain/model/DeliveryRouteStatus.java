package com.sparta.deliveryservice.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryRouteStatus {

    PENDING("대기", "배송 시작 전, 담당자 배정 대기"),
    IN_PROGRESS("이동 중", "허브 간 이동 중"),
    COMPLETED("완료", "허브 도착 완료");

    private final String description;
    private final String detail;
}
