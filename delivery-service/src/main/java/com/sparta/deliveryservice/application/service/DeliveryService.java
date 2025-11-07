package com.sparta.deliveryservice.application.service;

import com.sparta.deliveryservice.application.dto.CreateDeliveryCommand;
import com.sparta.deliveryservice.application.dto.CreateDeliveryResult;
import com.sparta.deliveryservice.application.dto.SearchDeliveryDetailResult;
import com.sparta.deliveryservice.domain.model.Delivery;
import com.sparta.deliveryservice.domain.model.DeliveryStatus;
import com.sparta.deliveryservice.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public CreateDeliveryResult createDelivery(CreateDeliveryCommand createDeliveryCommand) {
        deliveryRepository.findByOrderId(createDeliveryCommand.orderId()).
                ifPresent(delivery -> {
                    throw new IllegalArgumentException("이미 해당 주문에 대한 배송이 존재합니다.");
                });

        Delivery delivery = Delivery.of(
                createDeliveryCommand.orderId(),
                createDeliveryCommand.departureHubId(),
                createDeliveryCommand.arrivalHubId(),
                createDeliveryCommand.deliveryAddress(),
                createDeliveryCommand.receiverName(),
                createDeliveryCommand.receiverSlackId(),
                createDeliveryCommand.companyAgentId(),
                DeliveryStatus.HUB_WAITING);

        delivery = deliveryRepository.save(delivery);

        return CreateDeliveryResult.from(delivery);
    }

    public SearchDeliveryDetailResult searchDeliveryDetail(UUID deliveryId) {
        Delivery delivery = deliveryRepository.findById(deliveryId).orElseThrow(() ->
                new IllegalArgumentException("배송을 찾을수 없습니다."));

        return SearchDeliveryDetailResult.from(delivery);
    }
}