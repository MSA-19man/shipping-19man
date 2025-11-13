package com.sparta.deliveryservice.infrastructure.repository;

import com.sparta.deliveryservice.domain.model.DeliveryAgent;
import com.sparta.deliveryservice.domain.model.DeliveryAgentType;
import com.sparta.deliveryservice.domain.repository.DeliveryAgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DeliveryAgentRepositoryImpl implements DeliveryAgentRepository {
    private final DeliveryAgentJpaRepository jpaRepository;

    @Override
    public Optional<DeliveryAgent> findByAgentTypeAndDeliveryIndexAndDeletedAtIsNull(DeliveryAgentType agentType, Integer deliveryIndex) {
        return jpaRepository.findByAgentTypeAndDeliveryIndexAndDeletedAtIsNull(agentType,deliveryIndex);
    }

    @Override
    public Optional<DeliveryAgent> findByHubIdAndAgentTypeAndDeliveryIndexAndDeletedAtIsNull(UUID hubId, DeliveryAgentType agentType, Integer deliveryIndex) {
        return jpaRepository.findByHubIdAndAgentTypeAndDeliveryIndexAndDeletedAtIsNull(hubId, agentType, deliveryIndex);
    }

    @Override
    public List<DeliveryAgent> findAllByDeliveryIndexIsNotNullAndAgentTypeAndHubIdAndDeletedAtIsNull(DeliveryAgentType agentType, UUID hubId) {
        return jpaRepository.findAllByDeliveryIndexIsNotNullAndAgentTypeAndHubIdAndDeletedAtIsNull(agentType,hubId);
    }

    @Override
    public List<DeliveryAgent> findAllByDeliveryIndexIsNotNullAndAgentTypeAndDeletedAtIsNull(DeliveryAgentType agentType) {
        return jpaRepository.findAllByDeliveryIndexIsNotNullAndAgentTypeAndDeletedAtIsNull(agentType);
    }

    @Override
    public DeliveryAgent save(DeliveryAgent agent) {
        return jpaRepository.save(agent);
    }

}
