package com.sparta.deliveryservice.application.service;

import com.sparta.common.util.PageableUtil;
import com.sparta.deliveryservice.application.dto.*;
import com.sparta.deliveryservice.domain.model.Delivery;
import com.sparta.deliveryservice.domain.model.DeliveryRoute;
import com.sparta.deliveryservice.domain.model.DeliveryStatus;
import com.sparta.deliveryservice.domain.repository.DeliveryRepository;
import com.sparta.deliveryservice.domain.repository.DeliveryRouteRepository;
import com.sparta.deliveryservice.infrastructure.client.HubFeignClient;
import com.sparta.deliveryservice.infrastructure.client.dto.DeliveryRouteCalcResult;
import com.sparta.deliveryservice.infrastructure.client.dto.RouteSegmentDto;
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
    private final DeliveryRouteRepository deliveryRouteRepository;
    private final HubFeignClient hubFeignClient;

    @Transactional
    public CreateDeliveryResult createDelivery(CreateDeliveryCommand command) {
        if (deliveryRepository.existsByOrderIdAndDeletedAtIsNull(command.orderId())) {
            throw new IllegalArgumentException("이미 해당 주문에 대한 배송이 존재합니다.");
        }

        Delivery delivery = command.toEntity();

        delivery = deliveryRepository.save(delivery);
        createDeliveryRoute(delivery);
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
                yield deliveryRepository.findAllByDepartureHubIdOrArrivalHubIdAndDeletedAtIsNull(tempHubId, tempHubId, pageable);
            }

            case "DELIVERY_MANAGER", "COMPANY_MANAGER" -> {
                yield deliveryRepository.findAllByUserIdAndDeletedAtIsNull(tempUserId, pageable);
            }
            default -> throw new IllegalStateException("권한이 유효하지 않습니다.");
        };

        return deliveryPage.map(SearchDeliveryResult::from);
    }

    @Transactional
    public UpdateStatusDeliveryResult updateStatusDelivery(UUID deliveryId) {
        Delivery delivery = deliveryRepository.findByIdAndDeletedAtIsNull(deliveryId).orElseThrow(() ->
                new IllegalArgumentException("배송을 찾을수 없습니다."));

        DeliveryStatus beforeStatus = delivery.getStatus();
        DeliveryStatus nextStatus = getNextStatus(beforeStatus);
        delivery.updateStatus(nextStatus);
        deliveryRepository.save(delivery);
        return UpdateStatusDeliveryResult.from(delivery,beforeStatus);
    }

    /**
     * user와 security가 완성되는대로 업데이트 할 예정입니다.
     */
    @Transactional
    public DeleteDeliveryResult deleteDelivery(UUID deliveryId) {
        Delivery delivery = deliveryRepository.findByIdAndDeletedAtIsNull(deliveryId).orElseThrow(() ->
                new IllegalArgumentException("배송을 찾을수 없습니다."));

        delivery.markDeleted(delivery.getUserId()); //임시
        delivery = deliveryRepository.save(delivery);
        return DeleteDeliveryResult.from(delivery);
    }

    @Transactional
    public UpdateDeliveryResult updateDelivery(UpdateDeliveryCommand command, UUID deliveryId) {
        Delivery delivery = deliveryRepository.findByIdAndDeletedAtIsNull(deliveryId).orElseThrow(() ->
                new IllegalArgumentException("배송을 찾을수 없습니다."));

        delivery.updateDeliveryInfo(command);
        delivery = deliveryRepository.save(delivery);
        return UpdateDeliveryResult.from(delivery);
    }

    private DeliveryStatus getNextStatus(DeliveryStatus status) {
        return switch (status) {
            case HUB_WAITING -> DeliveryStatus.HUB_MOVING;
            case HUB_MOVING -> DeliveryStatus.HUB_ARRIVED;
            case HUB_ARRIVED -> DeliveryStatus.COMPANY_MOVING;
            case COMPANY_MOVING ->DeliveryStatus.COMPLETED;
            case COMPLETED -> throw new IllegalStateException("이미 배송이 완료되었습니다.");
            default -> throw new IllegalStateException("잘못된 요청입니다.");
        };
    }

    private void createDeliveryRoute(Delivery delivery){
        try {
            DeliveryRouteCalcResult routeCalcResult = hubFeignClient.findPath(
                    delivery.getDepartureHubId(),
                    delivery.getArrivalHubId()
            );
            if (routeCalcResult == null || routeCalcResult.segments() == null){
                throw new IllegalArgumentException("배송 경로 생성이 실패 하였습니다");
            }

            int sequence = 1;
            for (RouteSegmentDto segment : routeCalcResult.segments()){
                DeliveryRoute deliveryRoute = DeliveryRoute.of(
                        delivery,
                        segment.departureHubId(),
                        segment.arrivalHubId(),
                        sequence++,
                        segment.distance(),
                        segment.requiredTime()
                );
                deliveryRouteRepository.save(deliveryRoute);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}