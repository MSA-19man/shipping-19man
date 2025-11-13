package com.sparta.deliveryservice.infrastructure.repository;

import com.sparta.deliveryservice.domain.model.DeliveryAgent;
import com.sparta.deliveryservice.domain.model.DeliveryAgentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeliveryAgentJpaRepository extends JpaRepository<DeliveryAgent, UUID> {
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
}
