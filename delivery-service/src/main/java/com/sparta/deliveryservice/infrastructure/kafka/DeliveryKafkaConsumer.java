package com.sparta.deliveryservice.infrastructure.kafka;

import com.sparta.deliveryservice.application.dto.CreateDeliveryCommand;
import com.sparta.deliveryservice.application.dto.CreateDeliveryMessage;
import com.sparta.deliveryservice.application.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryKafkaConsumer {

    private final DeliveryService deliveryService;

    @KafkaListener(
            topics = "delivery.creation.requested",
            groupId = "delivery-service-group"
    )
    public void consumerCreateDelivery(CreateDeliveryMessage message, Acknowledgment acknowledgment){
        try {
            CreateDeliveryCommand command = message.toCommand();
            deliveryService.createDelivery(command);
            acknowledgment.acknowledge();
        } catch (Exception e) {
            acknowledgment.acknowledge();
            throw new RuntimeException(e);
        }
    }
}
