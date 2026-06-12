package com.FacilitiesService.wastelog;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WasteLogRepository extends JpaRepository<WasteLog, Long> {
    List<WasteLog> findByPickupRequestId(UUID pickupRequestId);
    List<WasteLog> findByFacilityId(Long facilityId);
}
