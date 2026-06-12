package com.logistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    private Long id;
    private String licensePlate;
    private VehicleType type;
    private Double capacity;
    private Boolean active;
    private Double currentLatitude;
    private Double currentLongitude;

    public enum VehicleType {
        DRY_WASTE, HAZARDOUS_WASTE, RECYCLABLE_WASTE
    }
}
