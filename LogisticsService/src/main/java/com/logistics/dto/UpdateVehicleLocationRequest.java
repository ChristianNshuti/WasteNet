package com.logistics.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicleLocationRequest {
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
}
