package com.FacilitiesService.wastelog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "waste_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WasteLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pickup_request_id", nullable = false)
    private UUID pickupRequestId;

    @Column(name = "facility_id", nullable = false)
    private Long facilityId;

    @Column(nullable = false)
    private String wasteType;

    @Column(nullable = false)
    private Double weight;

    @CreationTimestamp
    @Column(name = "arrived_at", nullable = false, updatable = false)
    private LocalDateTime arrivedAt;
}
