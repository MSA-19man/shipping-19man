package com.sparta.hubservice.infra.setup;


import com.sparta.hubservice.domain.model.Hub;
import com.sparta.hubservice.domain.model.HubRoute;
import com.sparta.hubservice.domain.repository.HubRepository;
import com.sparta.hubservice.domain.repository.HubRouteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class HubRouteInitializer implements ApplicationRunner {

    private final HubRepository hubRepository;
    private final HubRouteRepository hubRouteRepository;

    private static final String GYEONGGI_NAME  = "경기 남부 센터";
    private static final String DAEJEON_NAME = "대전광역시 센터";
    private static final String DAEGU_NAME = "대구광역시 센터";

    // 중앙 허브 간 경로
    private static final List<String[]> CENTRAL_SEGMENTS = List.of(
            new String[]{GYEONGGI_NAME, DAEJEON_NAME},
            new String[]{DAEJEON_NAME, DAEGU_NAME},
            new String[]{GYEONGGI_NAME, DAEGU_NAME}
    );

    // 저장된 각 허브들의 관할 허브 정하기 -> 계산해서
    // 정해진 각 허브들과의 세그먼트 저장하기(거리, 시간) - 각 2개 (허브->중앙허브/중앙허브->허브)

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception{

        if (hubRouteRepository.count() > 0) {
            log.info("H&S 세그먼트가 이미 존재 (초기화 건너뜀)");
            return;
        }

        List<Hub> allHubs = hubRepository.findAll();
        Map<String, Hub> hubMap = new HashMap<>();
        for(Hub hub : allHubs) {
            hubMap.put(hub.getName(), hub);
        }

        List<Hub> centralHubs = List.of(
                hubMap.get(GYEONGGI_NAME),
                hubMap.get(DAEJEON_NAME),
                hubMap.get(DAEGU_NAME)
        );

        log.info("세그먼트 계산 및 DB 저장 시작");
        List<HubRoute> routesToSave = new ArrayList<>();

        // 가까운 중앙 허브 배정
        for (Hub spokeHub : allHubs) {
            // 중앙 허브 패스
            if(spokeHub.getName().equals(GYEONGGI_NAME) ||
               spokeHub.getName().equals(DAEJEON_NAME) ||
               spokeHub.getName().equals(DAEGU_NAME)){
                continue;
            }

            Hub closestCentralHub = findClosestCentralHub(spokeHub, centralHubs);

            if (closestCentralHub != null){
                double distance= calculateDistance(
                        spokeHub.getLatitude(), spokeHub.getLongitude(),
                        closestCentralHub.getLatitude(), closestCentralHub.getLongitude()
                );

                // 트럭 속도를 70km/h로 계산
                int timeInMinutes = (int) ((distance / 70.0) * 60);

                routesToSave.add(HubRoute.of(spokeHub, closestCentralHub, distance, timeInMinutes));
                routesToSave.add(HubRoute.of(closestCentralHub, spokeHub, distance, timeInMinutes));
            }
        }

        // 중앙 허브끼리 계산
        for (String[] pair : CENTRAL_SEGMENTS) {
            Hub hubA = hubMap.get(pair[0]);
            Hub hubB = hubMap.get(pair[1]);

            if(hubA != null && hubB != null) {
                double distance = calculateDistance(
                        hubA.getLatitude(), hubA.getLongitude(),
                        hubB.getLatitude(), hubB.getLongitude()
                );
                int timeInMinutes = (int) ((distance / 70.0) * 60);

                routesToSave.add(HubRoute.of(hubA, hubB, distance, timeInMinutes));
                routesToSave.add(HubRoute.of(hubB, hubA, distance, timeInMinutes));
            }
        }

        hubRouteRepository.saveAll(routesToSave);
        log.info("세그먼트 초기 저장 완료");
    }

    private Hub findClosestCentralHub(Hub spokeHub, List<Hub> centralHubs) {
        Hub closestHub = null;
        double minDistance = Double.MAX_VALUE;

        for (Hub central : centralHubs) {
            double distance = calculateDistance(
                    spokeHub.getLatitude(), spokeHub.getLongitude(),
                    central.getLatitude(), central.getLongitude()
            );

            if (distance < minDistance) {
                minDistance = distance;
                closestHub = central;
            }
        }

        if(closestHub != null) {
            log.info("중앙 허브 배정 {} -> {}",
                    spokeHub.getName(), closestHub.getName());
        }

        return closestHub;
    }

    // 위도, 경도로 계산
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c; // (최종 거리 km)
    }
}
