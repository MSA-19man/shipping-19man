package com.sparta.deliveryservice.application.service;

import com.sparta.common.util.PageableUtil;
import com.sparta.deliveryservice.application.dto.SearchDeliveryRouteResult;
import com.sparta.deliveryservice.domain.model.DeliveryRoute;
import com.sparta.deliveryservice.domain.repository.DeliveryRouteRepository;
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
public class DeliveryRouteService {

    private final DeliveryRouteRepository deliveryRouteRepository;

    public Page<SearchDeliveryRouteResult> searchDeliveryRouteResult(UUID deliveryId,int page, int size, Sort.Direction direction) {
        if (deliveryRouteRepository.findByDeliveryIdAndDeletedAtIsNull(deliveryId))
            throw new IllegalStateException("해당 배송에 대한 배송 경로가 존재하지 않습니다.");

        Pageable pageable = PageableUtil.makePageable(page, size, PageableUtil.order(direction, "createdAt"));
        Page<DeliveryRoute> route = deliveryRouteRepository.findAllByDeliveryIdAndDeletedAtIsNull(deliveryId, pageable);
        return  route.map(SearchDeliveryRouteResult::from);
    }
}
