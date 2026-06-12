package com.logistics.controller;

import com.logistics.dto.CompletePickupRequest;
import com.logistics.dto.CreateRouteRequest;
import com.logistics.model.PickupAssignment;
import com.logistics.model.Route;
import com.logistics.service.RoutingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/routing")
@RequiredArgsConstructor
public class RoutingController {

    private final RoutingService routingService;

    @PostMapping("/routes")
    public Mono<ResponseEntity<Route>> createRoute(@Valid @RequestBody CreateRouteRequest request) {
        return routingService.createRoute(request)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/routes/{id}")
    public Mono<ResponseEntity<Route>> getRoute(@PathVariable Long id) {
        return routingService.getRoute(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/routes")
    public Flux<Route> getAllRoutes() {
        return routingService.getAllRoutes();
    }

    @GetMapping("/routes/driver/{driverId}")
    public Flux<Route> getRoutesByDriver(@PathVariable Long driverId) {
        return routingService.getRoutesByDriver(driverId);
    }

    @PutMapping("/routes/{id}/start")
    public Mono<ResponseEntity<Route>> startRoute(@PathVariable Long id) {
        return routingService.startRoute(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/routes/{id}/complete")
    public Mono<ResponseEntity<Route>> completeRoute(@PathVariable Long id) {
        return routingService.completeRoute(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/routes/{id}/assignments")
    public Flux<PickupAssignment> getAssignmentsByRoute(@PathVariable Long id) {
        return routingService.getAssignmentsByRoute(id);
    }

    @PutMapping("/assignments/{id}/complete")
    public Mono<ResponseEntity<PickupAssignment>> completePickup(@PathVariable Long id, @Valid @RequestBody CompletePickupRequest request) {
        return routingService.completePickup(id, request)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/assignments/pending")
    public Flux<PickupAssignment> getPendingAssignments() {
        return routingService.getPendingAssignments();
    }
}
