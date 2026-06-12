package com.waste.pickup.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePickupRequest {

    @NotNull(message = "Waste type is required")
    private String wasteType;

    @NotNull(message = "Latitude is required")
    private Double latitude;

    @NotNull(message = "Longitude is required")
    private Double longitude;
}
