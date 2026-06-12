package com.logistics.repository;

import com.logistics.model.PickupAssignment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface PickupAssignmentRepository extends ReactiveCrudRepository<PickupAssignment, Long> {
    Flux<PickupAssignment> findByRouteId(Long routeId);
    Mono<PickupAssignment> findByRequestId(UUID requestId);
    Flux<PickupAssignment> findByStatus(PickupAssignment.AssignmentStatus status);
}
