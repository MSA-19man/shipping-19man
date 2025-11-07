package com.sparta.deliveryservice.application.service;

import com.sparta.deliveryservice.domain.model.Delivery;
import com.sparta.deliveryservice.domain.model.DeliveryStatus;
import com.sparta.deliveryservice.domain.repository.DeliveryRepository;
import com.sparta.deliveryservice.presentation.request.DeliveryCreateRequest;
import com.sparta.deliveryservice.presentation.response.DeliveryCreateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeliveryService {

	private final DeliveryRepository deliveryRepository;

	@Transactional
	public DeliveryCreateResponse createDelivery(DeliveryCreateRequest request) {
		deliveryRepository.findByOrderId(request.getOrderId()).
			ifPresent(delivery -> {
				throw new IllegalArgumentException("이미 해당 주문에 대한 배송이 존재합니다.");
			});

		Delivery delivery = Delivery.of(
				request.getOrderId(),
				request.getDepartureHubId(),
				request.getArrivalHubId(),
				request.getDeliveryAddress(),
				request.getReceiverName(),
				request.getReceiverSlackId(),
				request.getCompanyAgentId(),
				DeliveryStatus.HUB_WAITING);

		delivery = deliveryRepository.save(delivery);

		return DeliveryCreateResponse.from(delivery);
	}
}