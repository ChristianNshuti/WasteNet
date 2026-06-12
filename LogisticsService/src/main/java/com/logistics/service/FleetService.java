package com.logistics.service;

import com.logistics.dto.CreateDriverRequest;
import com.logistics.dto.CreateVehicleRequest;
import com.logistics.dto.UpdateVehicleLocationRequest;
import com.logistics.model.Driver;
import com.logistics.model.Vehicle;
import com.logistics.repository.DriverRepository;
import com.logistics.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FleetService {

    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;

    public Mono<Vehicle> createVehicle(CreateVehicleRequest request) {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate(request.getLicensePlate());
        vehicle.setType(request.getType());
        vehicle.setCapacity(request.getCapacity());
        vehicle.setActive(true);
        vehicle.setCurrentLatitude(request.getCurrentLatitude());
        vehicle.setCurrentLongitude(request.getCurrentLongitude());
        return vehicleRepository.save(vehicle);
    }

    public Mono<Vehicle> getVehicle(Long id) {
        return vehicleRepository.findById(id);
    }

    public Flux<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Flux<Vehicle> getActiveVehicles() {
        return vehicleRepository.findByActiveTrue();
    }

    public Mono<Vehicle> updateVehicleLocation(Long id, UpdateVehicleLocationRequest request) {
        return vehicleRepository.findById(id)
                .flatMap(vehicle -> {
                    vehicle.setCurrentLatitude(request.getLatitude());
                    vehicle.setCurrentLongitude(request.getLongitude());
                    return vehicleRepository.save(vehicle);
                });
    }

    public Mono<Driver> createDriver(CreateDriverRequest request) {
        Driver driver = new Driver();
        driver.setName(request.getName());
        driver.setEmail(request.getEmail());
        driver.setPhone(request.getPhone());
        driver.setAvailable(true);
        return driverRepository.save(driver);
    }

    public Mono<Driver> getDriver(Long id) {
        return driverRepository.findById(id);
    }

    public Flux<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    public Flux<Driver> getAvailableDrivers() {
        return driverRepository.findByAvailableTrue();
    }

    public Mono<Driver> assignVehicleToDriver(Long driverId, Long vehicleId) {
        return driverRepository.findById(driverId)
                .zipWith(vehicleRepository.findById(vehicleId))
                .flatMap(tuple -> {
                    Driver driver = tuple.getT1();
                    driver.setVehicleId(vehicleId);
                    driver.setAvailable(false);
                    return driverRepository.save(driver);
                });
    }
}
