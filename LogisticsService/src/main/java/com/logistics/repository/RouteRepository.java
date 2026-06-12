package com.logistics.repository;

import com.logistics.model.Route;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RouteRepository extends ReactiveCrudRepository<Route, Long> {
    Flux<Route> findByDriverId(Long driverId);
    Flux<Route> findByStatus(Route.RouteStatus status);
}
