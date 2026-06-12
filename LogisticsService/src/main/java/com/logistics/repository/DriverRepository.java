package com.logistics.repository;

import com.logistics.model.Driver;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface DriverRepository extends ReactiveCrudRepository<Driver, Long> {
    Flux<Driver> findByAvailableTrue();
}
