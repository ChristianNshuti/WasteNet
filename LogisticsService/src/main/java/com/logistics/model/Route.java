package com.logistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("routes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Route {

    @Id
    private Long id;
    private Long driverId;
    private Long vehicleId;
    private RouteStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    public enum RouteStatus {
        SCHEDULED, IN_PROGRESS, COMPLETED
    }
}
