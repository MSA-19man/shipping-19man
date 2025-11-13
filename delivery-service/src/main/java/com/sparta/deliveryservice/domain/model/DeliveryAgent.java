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

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer deliveryIndex;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryAgentType agentType;

    public static DeliveryAgent createCompanyAgent(
            Long userId,
            UUID hubId,
            Integer index){
        if (hubId == null){
            throw new IllegalArgumentException("소속 허브를 입력해주세요.");
        }
        validateIndex(index);

        DeliveryAgent agent = new DeliveryAgent();
        agent.userId = userId;
        agent.hubId = hubId;
        agent.deliveryIndex = index;
        agent.agentType = DeliveryAgentType.COMPANY_AGENT;
        return agent;
    }

    public static DeliveryAgent createHubAgent(
            Long userId,
            Integer index){
        validateIndex(index);

        DeliveryAgent agent = new DeliveryAgent();
        agent.userId = userId;
        agent.hubId = null;
        agent.deliveryIndex = index;
        agent.agentType = DeliveryAgentType.HUB_AGENT;
        return agent;
    }



    private static void validateIndex(Integer index){
        if (index == null || index < 1 || index > 10){
            throw new IllegalArgumentException("현재 배송 담당자의 번호는 1~10번까지 입니다.");
        }
    }
}
