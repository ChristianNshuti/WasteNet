package com.FacilitiesService.reward;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rewards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(name = "points_cost", nullable = false)
    private Integer pointsCost;

    @Column(nullable = false)
    private Integer stock;
}
