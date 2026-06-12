package com.logistics.service;

import com.logistics.config.RabbitMQConfig;
import com.logistics.dto.CompletePickupRequest;
import com.logistics.dto.CreateRouteRequest;
import com.logistics.dto.PickupCompletedEvent;
import com.logistics.dto.PickupRequestedEvent;
import com.logistics.model.PickupAssignment;
import com.logistics.model.Route;
import com.logistics.repository.PickupAssignmentRepository;
import com.logistics.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoutingService {

    private final RouteRepository routeRepository;
    private final PickupAssignmentRepository pickupAssignmentRepository;
    private final RabbitTemplate rabbitTemplate;

    public Mono<Void> handlePickupRequested(PickupRequestedEvent event) {
        PickupAssignment assignment = new PickupAssignment();
        assignment.setRequestId(event.getRequestId());
        assignment.setCitizenId(event.getCitizenId());
        assignment.setWasteType(event.getWasteType());
        assignment.setLatitude(event.getLatitude());
        assignment.setLongitude(event.getLongitude());
        assignment.setStatus(PickupAssignment.AssignmentStatus.PENDING);
        return pickupAssignmentRepository.save(assignment).then();
    }

    public Mono<Route> createRoute(CreateRouteRequest request) {
        Route route = new Route();
        route.setDriverId(request.getDriverId());
        route.setVehicleId(request.getVehicleId());
        route.setStatus(Route.RouteStatus.SCHEDULED);
        route.setCreatedAt(LocalDateTime.now());
        return routeRepository.save(route)
                .flatMap(savedRoute -> {
                    return Flux.fromIterable(request.getPickupRequestIds())
                            .flatMap(requestId -> pickupAssignmentRepository.findByRequestId(requestId))
                            .flatMap(assignment -> {
                                assignment.setRouteId(savedRoute.getId());
                                assignment.setStatus(PickupAssignment.AssignmentStatus.ASSIGNED);
                                return pickupAssignmentRepository.save(assignment);
                            })
                            .then(Mono.just(savedRoute));
                });
    }

    public Mono<Route> startRoute(Long id) {
        return routeRepository.findById(id)
                .flatMap(route -> {
                    route.setStatus(Route.RouteStatus.IN_PROGRESS);
                    return routeRepository.save(route);
                });
    }

    public Mono<PickupAssignment> completePickup(Long assignmentId, CompletePickupRequest request) {
        return pickupAssignmentRepository.findById(assignmentId)
                .flatMap(assignment -> {
                    assignment.setStatus(PickupAssignment.AssignmentStatus.COLLECTED);
                    assignment.setCollectedWeight(request.getCollectedWeight());
                    return pickupAssignmentRepository.save(assignment)
                            .doOnNext(saved -> {
                                PickupCompletedEvent event = new PickupCompletedEvent(
                                        saved.getRequestId(),
                                        saved.getCitizenId(),
                                        saved.getWasteType(),
                                        saved.getCollectedWeight(),
                                        LocalDateTime.now()
                                );
                                rabbitTemplate.convertAndSend(
                                        RabbitMQConfig.EXCHANGE,
                                        RabbitMQConfig.PICKUP_COMPLETED_ROUTING_KEY,
                                        event
                                );
                            });
                });
    }

    public Mono<Route> completeRoute(Long id) {
        return routeRepository.findById(id)
                .flatMap(route -> {
                    route.setStatus(Route.RouteStatus.COMPLETED);
                    route.setCompletedAt(LocalDateTime.now());
                    return routeRepository.save(route);
                });
    }

    public Mono<Route> getRoute(Long id) {
        return routeRepository.findById(id);
    }

    public Flux<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    public Flux<Route> getRoutesByDriver(Long driverId) {
        return routeRepository.findByDriverId(driverId);
    }

    public Flux<PickupAssignment> getAssignmentsByRoute(Long routeId) {
        return pickupAssignmentRepository.findByRouteId(routeId);
    }

    public Flux<PickupAssignment> getPendingAssignments() {
        return pickupAssignmentRepository.findByStatus(PickupAssignment.AssignmentStatus.PENDING);
    }
}
