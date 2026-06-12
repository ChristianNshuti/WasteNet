package com.FacilitiesService.facility;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "facilities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FacilityType type;

    @Column(name = "max_capacity", nullable = false)
    private Double maxCapacity;

    @Column(name = "current_load", nullable = false)
    private Double currentLoad;
}
