package com.logistics.service;

import com.logistics.model.Vehicle;
import com.logistics.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class LiveTrackingService {

    private final VehicleRepository vehicleRepository;
    private final SimpMessagingTemplate messagingTemplate;

    @Scheduled(fixedRate = 5000)
    public void broadcastVehicleLocations() {
        vehicleRepository.findByActiveTrue()
                .collectList()
                .subscribe(vehicles -> {
                    messagingTemplate.convertAndSend("/topic/vehicle-locations", vehicles);
                });
    }
}
