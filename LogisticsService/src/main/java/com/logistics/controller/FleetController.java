package com.logistics.controller;

import com.logistics.dto.CreateDriverRequest;
import com.logistics.dto.CreateVehicleRequest;
import com.logistics.dto.UpdateVehicleLocationRequest;
import com.logistics.model.Driver;
import com.logistics.model.Vehicle;
import com.logistics.service.FleetService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/fleet")
@RequiredArgsConstructor
public class FleetController {

    private final FleetService fleetService;

    @PostMapping("/vehicles")
    public Mono<ResponseEntity<Vehicle>> createVehicle(@Valid @RequestBody CreateVehicleRequest request) {
        return fleetService.createVehicle(request)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/vehicles/{id}")
    public Mono<ResponseEntity<Vehicle>> getVehicle(@PathVariable Long id) {
        return fleetService.getVehicle(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/vehicles")
    public Flux<Vehicle> getAllVehicles() {
        return fleetService.getAllVehicles();
    }

    @GetMapping("/vehicles/active")
    public Flux<Vehicle> getActiveVehicles() {
        return fleetService.getActiveVehicles();
    }

    @PutMapping("/vehicles/{id}/location")
    public Mono<ResponseEntity<Vehicle>> updateVehicleLocation(@PathVariable Long id, @Valid @RequestBody UpdateVehicleLocationRequest request) {
        return fleetService.updateVehicleLocation(id, request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/drivers")
    public Mono<ResponseEntity<Driver>> createDriver(@Valid @RequestBody CreateDriverRequest request) {
        return fleetService.createDriver(request)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/drivers/{id}")
    public Mono<ResponseEntity<Driver>> getDriver(@PathVariable Long id) {
        return fleetService.getDriver(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/drivers")
    public Flux<Driver> getAllDrivers() {
        return fleetService.getAllDrivers();
    }

    @GetMapping("/drivers/available")
    public Flux<Driver> getAvailableDrivers() {
        return fleetService.getAvailableDrivers();
    }

    @PutMapping("/drivers/{driverId}/assign-vehicle/{vehicleId}")
    public Mono<ResponseEntity<Driver>> assignVehicleToDriver(@PathVariable Long driverId, @PathVariable Long vehicleId) {
        return fleetService.assignVehicleToDriver(driverId, vehicleId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
