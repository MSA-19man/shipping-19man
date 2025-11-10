package com.sparta.deliveryservice.application.service;

import com.sparta.common.util.PageableUtil;
import com.sparta.deliveryservice.application.dto.CreateDeliveryCommand;
import com.sparta.deliveryservice.application.dto.CreateDeliveryResult;
import com.sparta.deliveryservice.application.dto.SearchDeliveryDetailResult;
import com.sparta.deliveryservice.application.dto.SearchDeliveryResult;
import com.sparta.deliveryservice.domain.model.Delivery;
import com.sparta.deliveryservice.domain.model.DeliveryStatus;
import com.sparta.deliveryservice.domain.repository.DeliveryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
                createDeliveryCommand.userId(),
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
        Delivery delivery = deliveryRepository.findByIdAndDeletedAtIsNull(deliveryId).orElseThrow(() ->
                new IllegalArgumentException("배송을 찾을수 없습니다."));

        return SearchDeliveryDetailResult.from(delivery);
    }

    /**
     * 배송 목록 조회 (권한별 자동 필터링)
     * <p>
     * TODO: User Service 연동 후 임시 파라미터 제거하고 SecurityUtils 사용
     */
    public Page<SearchDeliveryResult> searchDelivery(Long tempUserId, String tempRole, UUID tempHubId, int page, int size, Sort.Direction direction) {

        Pageable pageable = PageableUtil.makePageable(page, size, PageableUtil.order(direction, "createdAt"));

        // TODO: SecurityUtils로 대체
        // Long currentUserId = SecurityUtils.getCurrentUserId();
        // String currentUserRole = SecurityUtils.getCurrentUserRole();
        // UUID currentUserHubId = SecurityUtils.getCurrentUserHubId();

        Page<Delivery> deliveryPage = switch (tempRole) {
            case "MASTER" -> {
                yield deliveryRepository.findAllByDeletedAtIsNull(pageable);
            }

            case "HUB_MANAGER" -> {
                yield deliveryRepository.findAllByDepartureHubIdOrArrivalHubIdAndDeletedAtIsNull(tempHubId,tempHubId,pageable);
            }

            case "DELIVERY_MANAGER","COMPANY_MANAGER" -> {
                yield deliveryRepository.findAllByUserIdAndDeletedAtIsNull(tempUserId,pageable);
            }
            default -> throw new IllegalStateException("권한이 유효하지 않습니다.");
        };

        return deliveryPage.map(SearchDeliveryResult::from);
    }
}