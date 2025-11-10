package com.sparta.deliveryservice.domain.model;

import com.sparta.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "p_delivery")
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID orderId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private UUID departureHubId;

    @Column(nullable = false)
    private UUID arrivalHubId;

    @Column(nullable = false)
    private String deliveryAddress;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private String receiverSlackId;

    @Column(nullable = false)
    private UUID companyAgentId;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    public void updateStatus(DeliveryStatus status) {
        this.status = status;
    }

    @Builder(access = AccessLevel.PRIVATE)
    private Delivery(UUID orderId,
                     Long userId,
                     UUID departureHubId,
                     UUID arrivalHubId,
                     String deliveryAddress,
                     String receiverName,
                     String receiverSlackId,
                     UUID companyAgentId,
                     DeliveryStatus status) {
        this.orderId = orderId;
        this.userId = userId;
        this.departureHubId = departureHubId;
        this.arrivalHubId = arrivalHubId;
        this.deliveryAddress = deliveryAddress;
        this.receiverName = receiverName;
        this.receiverSlackId = receiverSlackId;
        this.companyAgentId = companyAgentId;
        this.status = DeliveryStatus.HUB_WAITING;
    }

    public static Delivery of(UUID orderId,
                              Long userId,
                              UUID departureHubId,
                              UUID arrivalHubId,
                              String deliveryAddress,
                              String receiverName,
                              String receiverSlackId,
                              UUID companyAgentId) {
        return Delivery.builder()
                .orderId(orderId)
                .userId(userId)
                .departureHubId(departureHubId)
                .arrivalHubId(arrivalHubId)
                .deliveryAddress(deliveryAddress)
                .receiverName(receiverName)
                .receiverSlackId(receiverSlackId)
                .companyAgentId(companyAgentId)
                .build();
    }
}
