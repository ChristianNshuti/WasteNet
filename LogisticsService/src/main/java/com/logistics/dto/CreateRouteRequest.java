package com.logistics.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRouteRequest {
    @NotNull
    private Long driverId;
    @NotNull
    private Long vehicleId;
    @NotNull
    private List<UUID> pickupRequestIds;
}
