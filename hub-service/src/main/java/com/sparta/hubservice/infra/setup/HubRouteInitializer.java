package com.sparta.hubservice.infra.setup;


import com.sparta.hubservice.domain.model.Hub;
import com.sparta.hubservice.domain.model.HubRoute;
import com.sparta.hubservice.domain.repository.HubRepository;
import com.sparta.hubservice.domain.repository.HubRouteRepository;
import com.sparta.hubservice.infra.repository.JpaRepository.HubRouteJpaRepository;
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

    private static final Map<String, String> hubToCentral = new HashMap<>();
    private static final Map<String, Map<String, RouteInfo>> segments = new HashMap<>();

    private static final String GYEONGGI  = "경기 남부 센터";
    private static final String DAEJEON = "대전광역시 센터";
    private static final String DAEGU = "대구광역시 센터";

    private static void addSegment(String dep, String arr, double dist, int time) {
        // (양방향으로 segments 맵에 데이터 추가...)
        segments.computeIfAbsent(dep, k -> new HashMap<>()).put(arr, new RouteInfo(dist, time));
        segments.computeIfAbsent(arr, k -> new HashMap<>()).put(dep, new RouteInfo(dist, time));
    }

    private record RouteInfo(double distance, int timeInMinutes) {}

    // 1. 클래스 로드 시 H&S 규칙과 (임시) 데이터를 미리 정의
    static {
        // H&S 소속 정의
        hubToCentral.put("경기 북부 센터", GYEONGGI);
        hubToCentral.put("서울특별시 센터", GYEONGGI);
        hubToCentral.put("인천광역시 센터", GYEONGGI);
        hubToCentral.put("강원특별자치도 센터", GYEONGGI);
        hubToCentral.put(GYEONGGI, GYEONGGI);

        hubToCentral.put("충청남도 센터", DAEJEON);
        hubToCentral.put("충청북도 센터", DAEJEON);
        hubToCentral.put("세종특별자치시 센터", DAEJEON);
        hubToCentral.put("전북특별자치도 센터", DAEJEON);
        hubToCentral.put("광주광역시 센터", DAEJEON);
        hubToCentral.put("전라남도 센터", DAEJEON);
        hubToCentral.put(DAEJEON, DAEJEON);

        hubToCentral.put("경상북도 센터", DAEGU);
        hubToCentral.put("경상남도 센터", DAEGU);
        hubToCentral.put("부산광역시 센터", DAEGU);
        hubToCentral.put("울산광역시 센터", DAEGU);
        hubToCentral.put(DAEGU, DAEGU);

        // H&S "지도 조각" (세그먼트) 데이터 (임시 값)
        // (실제로는 Google/Gemini API로 이 구간들의 실제 값을 조회해서 채워야 함)
        addSegment("서울특별시 센터", GYEONGGI, 30.0, 45);
        addSegment("세종특별자치시 센터", DAEJEON, 20.0, 20);
        addSegment("부산광역시 센터", DAEGU, 80.0, 70);
        // ... (나머지 14개 로컬 허브 <-> 중앙 허브 구간도 모두 추가해야 함) ...
        addSegment(GYEONGGI, DAEJEON, 120.0, 90);
        addSegment(DAEJEON, DAEGU, 100.0, 80);
        addSegment(GYEONGGI, DAEGU, 220.0, 160); // (우회로)
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

        if(hubRouteRepository.count() > 0) {
            log.info("H&S 세그먼트가 DB에 존자합니다. (초기화 건너뜀)");
            return;
        }

        List<Hub> allHubs = hubRepository.findAll();
        Map<String, Hub> hubMap = new HashMap<>();
        for (Hub hub : allHubs){
            hubMap.put(hub.getName(), hub);
        }

        log.info("H&S 세그먼트 DB 저장 시작");
        List<HubRoute> routesToSave = new ArrayList<>();

        for (Map.Entry<String, Map<String, RouteInfo>> depEntry : segments.entrySet()){
            String depHubName = depEntry.getKey();
            Hub depHub = hubMap.get(depHubName);
            if (depHub == null) continue;

            for(Map.Entry<String, RouteInfo> arrEntry : depEntry.getValue().entrySet()){
                String arrHubName = arrEntry.getKey();
                RouteInfo info = arrEntry.getValue();
                Hub arrHub = hubMap.get(arrHubName);
                if (arrHub == null) continue;

                HubRoute route = HubRoute.of(depHub, arrHub, info.distance(), info.timeInMinutes());
                routesToSave.add(route);
            }
        }

        hubRouteRepository.saveAll(routesToSave);
        log.info("H&S 세그먼트 저장 완료: ({})", routesToSave.size());
    }
}
