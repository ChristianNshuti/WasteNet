package com.logistics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PickupCompletedEvent {
    private UUID requestId;
    private Long citizenId;
    private String wasteType;
    private Double collectedWeight;
    private LocalDateTime completedAt;
}
