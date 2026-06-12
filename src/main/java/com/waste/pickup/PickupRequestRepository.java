package com.waste.pickup;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PickupRequestRepository extends JpaRepository<PickupRequest, UUID> {
}
