package com.logistics.dto;

import com.logistics.model.Vehicle;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicleRequest {
    @NotBlank
    private String licensePlate;
    @NotNull
    private Vehicle.VehicleType type;
    @NotNull
    private Double capacity;
    @NotNull
    private Double currentLatitude;
    @NotNull
    private Double currentLongitude;
}
