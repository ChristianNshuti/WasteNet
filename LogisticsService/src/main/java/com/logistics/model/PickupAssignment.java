package com.logistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("pickup_assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickupAssignment {

    @Id
    private Long id;
    private UUID requestId;
    private Long citizenId;
    private String wasteType;
    private Double latitude;
    private Double longitude;
    private Long routeId;
    private AssignmentStatus status;
    private Double collectedWeight;

    public enum AssignmentStatus {
        PENDING, ASSIGNED, COLLECTED
    }
}
