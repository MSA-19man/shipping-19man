package com.sparta.deliveryservice.domain.model;

import com.sparta.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Table(name = "p_delivery_agent")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryAgent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID hubId;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private Integer deliveryIndex;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryAgentType agentType;

}
