package com.sparta.deliveryservice.domain.model;

import com.sparta.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "p_delivery_route")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeliveryRoute extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Column(nullable = false)
    private UUID departureHubId;

    @Column(nullable = false)
    private UUID arrivalHubId;

    @Column(nullable = false)
    private Integer sequence;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryRouteStatus status;

    @Column(nullable = false)
    private Double estimatedDistance;

    @Column(nullable = false)
    private Integer estimatedDuration;

    private Double actualDistance;

    private Integer actualDuration;


    public static DeliveryRoute of(
            Delivery delivery,
            UUID departureHubId,
            UUID arrivalHubId,
            Integer sequence,
            Double estimatedDistance,
            Integer estimatedDuration

    ) {
        DeliveryRoute route = new DeliveryRoute();
        route.delivery = delivery;
        route.departureHubId = departureHubId;
        route.arrivalHubId = arrivalHubId;
        route.sequence = sequence;
        route.estimatedDistance = estimatedDistance;
        route.estimatedDuration = estimatedDuration;
        route.status = DeliveryRouteStatus.PENDING;
        return route;
    }

    public void updateActualRouteInfo(Double distance, Integer duration){
        this.actualDistance = distance;
        this.actualDuration = duration;
    }
}
