package com.sparta.deliveryservice.domain.repository;

import com.sparta.deliveryservice.domain.model.DeliveryAgent;
import com.sparta.deliveryservice.domain.model.DeliveryAgentType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryAgentRepository {

    Optional<DeliveryAgent> findByAgentTypeAndDeliveryIndexAndDeletedAtIsNull(
            DeliveryAgentType agentType,
            Integer deliveryIndex
    );
    Optional<DeliveryAgent> findByHubIdAndAgentTypeAndDeliveryIndexAndDeletedAtIsNull(
            UUID hubId,
            DeliveryAgentType agentType,
            Integer deliveryIndex
    );

    List<DeliveryAgent> findAllByDeliveryIndexIsNotNullAndAgentTypeAndHubIdAndDeletedAtIsNull(
            DeliveryAgentType agentType,
            UUID hubId
    );

    List<DeliveryAgent> findAllByDeliveryIndexIsNotNullAndAgentTypeAndDeletedAtIsNull(
            DeliveryAgentType agentType
    );

    DeliveryAgent save(DeliveryAgent agent);
}
