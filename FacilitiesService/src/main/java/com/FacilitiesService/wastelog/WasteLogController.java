package com.FacilitiesService.wastelog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/waste-logs")
public class WasteLogController {

    @Autowired
    private WasteLogService wasteLogService;

    @GetMapping
    public List<WasteLog> getAllWasteLogs() {
        return wasteLogService.getAllWasteLogs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteLog> getWasteLogById(@PathVariable Long id) {
        return wasteLogService.getWasteLogById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pickup/{pickupRequestId}")
    public List<WasteLog> getWasteLogsByPickupRequestId(@PathVariable UUID pickupRequestId) {
        return wasteLogService.getWasteLogsByPickupRequestId(pickupRequestId);
    }

    @GetMapping("/facility/{facilityId}")
    public List<WasteLog> getWasteLogsByFacilityId(@PathVariable Long facilityId) {
        return wasteLogService.getWasteLogsByFacilityId(facilityId);
    }

    @PostMapping
    public WasteLog createWasteLog(@RequestBody WasteLog wasteLog) {
        return wasteLogService.createWasteLog(wasteLog);
    }
}
