package com.sparta.hubservice.domain.model;

import com.sparta.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "p_hub_route")
public class HubRoute extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "departure_hub_id", nullable = false)
    private Hub departureHub;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "arrival_hub_id", nullable = false)
    private Hub arrivalHub;

    @Column(nullable = false)
    private Double distance;

    @Column(nullable = false)
    private Integer requiredTime;

    @Builder
    private HubRoute(Hub departureHub, Hub arrivalHub, Double distance, Integer requiredTime){
        this.departureHub = departureHub;
        this.arrivalHub = arrivalHub;
        this.distance = distance;
        this.requiredTime = requiredTime;
    }

    public static HubRoute of(Hub departureHub, Hub arrivalHub, Double distance, Integer requiredTime){
        return HubRoute.builder()
                .departureHub(departureHub)
                .arrivalHub(arrivalHub)
                .distance(distance)
                .requiredTime(requiredTime)
                .build();
    }
}
