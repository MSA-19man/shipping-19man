package com.sparta.deliveryservice.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DeliveryAgentType {

    HUB_AGENT("허브 배송 담당자"),
    COMPANY_AGENT("업체 배송 담당자");

    private final String displayName;
}
