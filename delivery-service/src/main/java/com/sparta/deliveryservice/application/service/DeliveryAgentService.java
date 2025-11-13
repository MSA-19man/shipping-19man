package com.sparta.deliveryservice.application.service;

import com.sparta.deliveryservice.application.dto.CreateDeliveryAgentCommand;
import com.sparta.deliveryservice.application.dto.CreateDeliveryAgentResult;
import com.sparta.deliveryservice.domain.model.DeliveryAgent;
import com.sparta.deliveryservice.domain.model.DeliveryAgentType;
import com.sparta.deliveryservice.domain.repository.DeliveryAgentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryAgentService {

    private final DeliveryAgentRepository deliveryAgentRepository;
    private final Map<String, Queue<Integer>> AgentMap = new HashMap<>();


    /**
     * 업체 배송 담당자 배정
     */
    public UUID assignCompanyAgent(UUID hubId) {
        String key = ""+hubId;
        Queue<Integer> companyIndexQueue = AgentMap.computeIfAbsent(key, k -> initQueue());
        for (int i = 0; i < 10; i++){
            Integer targetNum = companyIndexQueue.poll();

            Optional<DeliveryAgent> agent = deliveryAgentRepository
                    .findByHubIdAndAgentTypeAndDeliveryIndexAndDeletedAtIsNull(hubId, DeliveryAgentType.COMPANY_AGENT,targetNum);
            companyIndexQueue.add(targetNum);

            if (agent.isPresent()){
                return agent.get().getId();
            }
        }
        throw new IllegalArgumentException("업체 배송 담당자가 배정되지 않았습니다.");
    }

    /**
     *  허브 배송 담당자 배정
     */
    public UUID assignHubAgent(){
        String key = "HubAgent";
        Queue<Integer> hubIndexQueue = AgentMap.computeIfAbsent(key, k -> initQueue());

        for (int i = 0; i < 10; i++) {
            Integer targetNum =  hubIndexQueue.poll();

            Optional<DeliveryAgent> agent = deliveryAgentRepository
                    .findByAgentTypeAndDeliveryIndexAndDeletedAtIsNull(DeliveryAgentType.HUB_AGENT,targetNum);
            hubIndexQueue.add(targetNum);

            if (agent.isPresent()){
                return agent.get().getId();
            }
        }
        throw new IllegalArgumentException("허브 배송 담당자가 배정되지 않았습니다.");
    }

    @Transactional
    public CreateDeliveryAgentResult createDeliveryAgent(CreateDeliveryAgentCommand command){
        Integer emptyIndex;
        if (command.hubId() == null){
            emptyIndex = findEmptyHubAgentIndex();
        } else {
            emptyIndex = findEmptyCompanyAgentIndex(command.hubId());
        }
        if (emptyIndex == null){
            throw new IllegalArgumentException("배송담당자가 모두 배정되어있습니다 (최대10명)");
        }
        DeliveryAgent agent = command.toEntity(emptyIndex);
        deliveryAgentRepository.save(agent);
        return CreateDeliveryAgentResult.from(agent);
    }


    private Queue<Integer> initQueue() {
        Queue<Integer> queue = new LinkedList<>();
        IntStream.range(1,11).forEach(queue::add);
        return queue;
    }

    private Integer findEmptyHubAgentIndex() {
        List<Integer> findIndex = IntStream.range(1,11)
                .boxed()
                .toList();

        List<Integer> useIndex = deliveryAgentRepository.findAllByDeliveryIndexIsNotNullAndAgentTypeAndDeletedAtIsNull(
                DeliveryAgentType.HUB_AGENT)
                .stream()
                .map(DeliveryAgent::getDeliveryIndex)
                .toList();

        return findIndex.stream()
                .filter(index -> !useIndex.contains(index))
                .sorted()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("허브 배송 담당자는 최대 10명까지 배정 가능합니다."));
    }

    private Integer findEmptyCompanyAgentIndex(UUID hubId) {
        List<Integer> findIndex = IntStream.range(1,11)
                .boxed()
                .toList();

        List<Integer> useIndex = deliveryAgentRepository.findAllByDeliveryIndexIsNotNullAndAgentTypeAndHubIdAndDeletedAtIsNull(
                DeliveryAgentType.COMPANY_AGENT,hubId)
                .stream()
                .map(DeliveryAgent::getDeliveryIndex)
                .toList();

        return findIndex.stream()
                .filter(index -> !useIndex.contains(index))
                .sorted()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("업체 배송 담당자는 허브당 최대 10명까지 배정 가능합니다."));
    }
}
