package com.FacilitiesService.ecopoint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EcoPointRepository extends JpaRepository<EcoPoint, Long> {
    List<EcoPoint> findByCitizenId(Long citizenId);

    @Query("SELECT SUM(ep.points) FROM EcoPoint ep WHERE ep.citizenId = :citizenId")
    Integer getTotalPointsByCitizenId(@Param("citizenId") Long citizenId);
}
