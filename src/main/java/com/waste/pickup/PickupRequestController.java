package com.waste.pickup;

import com.waste.pickup.dto.CreatePickupRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pickups")
public class PickupRequestController {

    @Autowired
    private PickupRequestService pickupRequestService;

    @PostMapping
    public ResponseEntity<?> createPickupRequest(@Valid @RequestBody CreatePickupRequest request) {
        PickupRequest pickupRequest = pickupRequestService.createPickupRequest(request);
        return ResponseEntity.ok(pickupRequest);
    }
}
