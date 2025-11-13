package com.sparta.hubservice.application.service;

import com.sparta.hubservice.application.dto.DijkstraRouteResult;
import com.sparta.hubservice.application.dto.RouteSegmentDto;
import com.sparta.hubservice.domain.model.Hub;
import com.sparta.hubservice.domain.model.HubRoute;
import com.sparta.hubservice.domain.repository.HubRepository;
import com.sparta.hubservice.domain.repository.HubRouteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DijkstraService {

    private final HubRepository hubRepository;
    private final HubRouteRepository hubRouteRepository;

    private record Node(Hub hub, int time) implements Comparable<Node> {
        @Override
        public int compareTo(Node other){
            // 더 짧은 time이 우선순위 높도록
            return Integer.compare(this.time, other.time);
        }
    }

    // departure/arrival이 아닌 start/end : 경로 전체의 시작/끝 허브 말하므로 혼동하지 않기 위해
    public DijkstraRouteResult findPath(UUID startHubId, UUID endHubId) {
        List<HubRoute> allOpenSegments = hubRouteRepository.findAll();

        Map<UUID, List<HubRoute>> graph = new HashMap<>();
        for (HubRoute segment : allOpenSegments) {
            graph.computeIfAbsent(segment.getDepartureHub().getId(), k -> new ArrayList<>())
                    .add(segment);
        }
        // 각 출발허브 별로 segment를 저장 (키로 구별하여 없으면 해당 그래프 생성해줌)

        PriorityQueue<Node> pq = new PriorityQueue<>();
        Map<UUID, Integer> minTimes = new HashMap<>(); //출발 허브에서 각 허브까지 걸리는 최단 시긴 기록(현재까지의)
        Map<UUID, HubRoute> bestPathSegments = new HashMap<>(); //특정 허브에 가장 빠르게 도착하는 직전 경로 segment

        List<Hub> allHubs = hubRepository.findAll();
        for (Hub hub : allHubs) {
            minTimes.put(hub.getId(), Integer.MAX_VALUE);
        }

        Hub startHub = hubRepository.findById(startHubId)
                .orElseThrow(() -> new EntityNotFoundException("출발 허브를 찾을 수 없습니다."));
        minTimes.put(startHubId, 0);
        pq.add(new Node(startHub, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            UUID currentHubId = currentNode.hub().getId();
            int currentTime = currentNode.time();

            if (currentTime > minTimes.get(currentHubId)) continue;

            if (graph.containsKey(currentHubId)) {
                for (HubRoute segment : graph.get(currentHubId)) {
                    Hub neighborHub = segment.getArrivalHub();
                    int segmentTime = segment.getRequiredTime();
                    int newTime = currentTime + segmentTime;

                    if (newTime < minTimes.get(neighborHub.getId())) {
                        minTimes.put(neighborHub.getId(), newTime);
                        bestPathSegments.put(neighborHub.getId(), segment);
                        pq.add(new Node(neighborHub, newTime));
                    }
                }
            }
        }

        //역으로 (경로)
        return reconstructPath(startHubId, endHubId, minTimes, bestPathSegments);
    }

    private DijkstraRouteResult reconstructPath(UUID startHubId, UUID endHubId,
                                                Map<UUID, Integer> minTimes,
                                                Map<UUID, HubRoute> bestPathSegments) {

        List<RouteSegmentDto> segments = new ArrayList<>();
        double totalDistance = 0.0;
        int totalTime = minTimes.get(endHubId);

        if (totalTime == Integer.MAX_VALUE) {
            log.warn("경로를 찾을 수 없습니다. (출발: {}, 도착: {})", startHubId, endHubId);
            return new DijkstraRouteResult(Collections.emptyList(), 0, -1);
        }

        UUID currentHubId = endHubId;

        // 도착~출발 거슬러 올라감
        while (bestPathSegments.containsKey(currentHubId)) {

            HubRoute segment = bestPathSegments.get(currentHubId);

            segments.add(RouteSegmentDto.from(segment));
            totalDistance += segment.getDistance();

            currentHubId = segment.getDepartureHub().getId(); // 이전 허브로 이동
        }

        Collections.reverse(segments);

        return new DijkstraRouteResult(segments, totalDistance, totalTime);
    }

}
