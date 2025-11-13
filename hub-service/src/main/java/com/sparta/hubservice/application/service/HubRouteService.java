package com.sparta.hubservice.application.service;

import com.sparta.hubservice.domain.model.Hub;
import com.sparta.hubservice.domain.model.HubRoute;
import com.sparta.hubservice.domain.repository.HubRepository;
import com.sparta.hubservice.domain.repository.HubRouteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HubRouteService {

    private final HubRepository hubRepository;
    private final HubRouteRepository hubRouteRepository;

    private static final List<String> CENTRAL_HUB_NAMES = Arrays.asList(
            "경기 남부 센터", "대전광역시 센터", "대구광역시 센터"
    );

    @Transactional
    public void createSegmentsForNewHub(Hub newHub) {
        log.info("관할 중앙 허브 배정 시작");
        // 관할 중앙 허브 찾고
        // 중앙 허브와의 거리/시간 구하고
        // 세그먼트 두개 생성

        // 중앙 허브 조회
        List<Hub> centralHubs = hubRepository.findAllByNameIn(CENTRAL_HUB_NAMES);

        if (centralHubs.size() < 3) {
            log.error("필수 중앙 허브 3개 DB 부재");
            throw new IllegalStateException("중앙 허브 데이터 누락");
        }

        Hub assignedCentralHub = findClosetHub(newHub, centralHubs);

        log.info("중앙 허브 배정 결과 ({})", assignedCentralHub.getName());

        // 세그먼트 생성 (허브->중앙허브, 중앙허브->허브)
        // Todo ai로 실제값 조회해오기 -> 일단은 하드코딩
//        double dummyDistance = 10.0;
//        int dummyTime = 10;

        double realDistance = calculateDistance(
                newHub.getLatitude(), newHub.getLongitude(),
                assignedCentralHub.getLatitude(), assignedCentralHub.getLongitude()
        );

        int realTimeInMinutes = (int) ((realDistance/70.0)*60);

        HubRoute segment1 = HubRoute.of(newHub, assignedCentralHub, realDistance, realTimeInMinutes);
        HubRoute segment2 = HubRoute.of(assignedCentralHub, newHub, realDistance, realTimeInMinutes);

        hubRouteRepository.saveAll(Arrays.asList(segment1, segment2));
        log.info("세그먼트 생성 완료");
    }

//    //Todo
//    /**
//     * [신규] "파업/화재" 등으로 경로(세그먼트)를 닫습니다. (논리적 삭제)
//     */
//    @Transactional // 쓰기 작업
//    public void closeRoute(UUID routeId, Long adminId) { // (adminId는 예시, 실제로는 @AuthenticationPrincipal)
//        log.warn("경로(세그먼트) {}를 관리자({})가 닫습니다(논리삭제).", routeId, adminId);
//
//        HubRoute route = hubRouteRepository.findById(routeId)
//                .orElseThrow(() -> new EntityNotFoundException("해당 경로(세그먼트)를 찾을 수 없습니다."));
//
//        route.markDeleted(adminId); // BaseEntity의 논리적 삭제 메서드 호출
//    }
//
//    /**
//     * [신규] "파업" 등이 끝나 닫힌 경로(세그먼트)를 다시 엽니다. (논리적 삭제 복구)
//     */
//    @Transactional // 쓰기 작업
//    public void openRoute(UUID routeId) {
//        log.info("경로(세그먼트) {}를 다시 엽니다(복구).", routeId);
//
//        // (주의: findById는 @Where로 논리삭제된 것을 못 찾습니다.
//        //  -> JpaRepository에 @Query로 deleted_at IS NOT NULL인 것을 찾는 메서드가 별도 필요)
//        // HubRoute route = hubRouteRepository.findByIdIncludingDeleted(routeId)...
//
//        // (임시 로직: 일단 findById로 가정)
//        HubRoute route = hubRouteRepository.findById(routeId)
//                .orElseThrow(() -> new EntityNotFoundException("해당 경로(세그먼트)를 찾을 수 없습니다."));
//
//        route.restoreDeletion(); // BaseEntity의 논리적 삭제 복구 메서드 호출
//    }

    private Hub findClosetHub(Hub newHub, List<Hub> candidates) {
        Hub closestHub = null;
        double minDistance = Double.MAX_VALUE;

        for(Hub candidate : candidates) {
            double distance = calculateDistance(newHub.getLatitude(), newHub.getLongitude(), candidate.getLatitude(), candidate.getLongitude());

            if (distance < minDistance) {
                minDistance = distance;
                closestHub = candidate;
            }
        }
        return closestHub;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2){
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // km 단위
    }
}
