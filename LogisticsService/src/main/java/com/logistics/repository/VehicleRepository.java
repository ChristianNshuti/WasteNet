package com.logistics.repository;

import com.logistics.model.Vehicle;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface VehicleRepository extends ReactiveCrudRepository<Vehicle, Long> {
    Flux<Vehicle> findByActiveTrue();
}
