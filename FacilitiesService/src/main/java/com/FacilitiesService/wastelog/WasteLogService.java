package com.FacilitiesService.wastelog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WasteLogService {

    @Autowired
    private WasteLogRepository wasteLogRepository;

    public List<WasteLog> getAllWasteLogs() {
        return wasteLogRepository.findAll();
    }

    public Optional<WasteLog> getWasteLogById(Long id) {
        return wasteLogRepository.findById(id);
    }

    public List<WasteLog> getWasteLogsByPickupRequestId(UUID pickupRequestId) {
        return wasteLogRepository.findByPickupRequestId(pickupRequestId);
    }

    public List<WasteLog> getWasteLogsByFacilityId(Long facilityId) {
        return wasteLogRepository.findByFacilityId(facilityId);
    }

    public WasteLog createWasteLog(WasteLog wasteLog) {
        return wasteLogRepository.save(wasteLog);
    }
}
