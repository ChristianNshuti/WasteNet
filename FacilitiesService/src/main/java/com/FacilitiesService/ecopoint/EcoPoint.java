package com.FacilitiesService.ecopoint;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "eco_points")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EcoPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "citizen_id", nullable = false)
    private Long citizenId;

    @Column(name = "pickup_request_id", nullable = false)
    private UUID pickupRequestId;

    @Column(nullable = false)
    private Integer points;

    @CreationTimestamp
    @Column(name = "earned_at", nullable = false, updatable = false)
    private LocalDateTime earnedAt;
}
