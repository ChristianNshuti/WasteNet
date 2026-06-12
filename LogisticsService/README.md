# Logistics Service - Waste Management System

## Overview
This is the Logistics & Route Optimization Service for the Waste Management System. It is built using **Spring Boot 3.4.1** with **Spring WebFlux** and **Spring Data R2DBC** for reactive programming.

## Features
- **Fleet Management**: Manage vehicles and drivers
- **Route Optimization**: Create and manage collection routes
- **Real-time Tracking**: WebSocket-based live vehicle location updates
- **Event-driven Architecture**: Integrates with other services via RabbitMQ
- **Pickup Assignment**: Listen for pickup requests and assign them to routes
- **Pickup Completion**: Notify other services when pickups are completed

## Tech Stack
- Java 21
- Spring Boot 3.4.1
- Spring WebFlux
- Spring Data R2DBC
- PostgreSQL (R2DBC driver)
- RabbitMQ
- Spring WebSocket (STOMP)
- Lombok

## Prerequisites
- Java 21
- PostgreSQL database (running locally on port 5432, database name: `waste_management`)
- RabbitMQ (running locally on port 5672, default credentials: guest/guest)

## Configuration
All configuration is in `src/main/resources/application.properties`:
```properties
spring.application.name=logistics-service
spring.r2dbc.url=r2dbc:postgresql://localhost:5432/waste_management
spring.r2dbc.username=postgres
spring.r2dbc.password=postgres
spring.rabbitmq.host=localhost
spring.rabbitmq.port=5672
spring.rabbitmq.username=guest
spring.rabbitmq.password=guest
server.port=8081
```

## API Endpoints

### Fleet Management (`/api/fleet`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/vehicles` | Create a new vehicle |
| GET | `/vehicles` | Get all vehicles |
| GET | `/vehicles/active` | Get all active vehicles |
| GET | `/vehicles/{id}` | Get vehicle by ID |
| PUT | `/vehicles/{id}/location` | Update vehicle location |
| POST | `/drivers` | Create a new driver |
| GET | `/drivers` | Get all drivers |
| GET | `/drivers/available` | Get all available drivers |
| GET | `/drivers/{id}` | Get driver by ID |
| PUT | `/drivers/{driverId}/assign-vehicle/{vehicleId}` | Assign vehicle to driver |

### Routing (`/api/routing`)
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/routes` | Create a new route |
| GET | `/routes` | Get all routes |
| GET | `/routes/{id}` | Get route by ID |
| GET | `/routes/driver/{driverId}` | Get routes by driver ID |
| PUT | `/routes/{id}/start` | Start a route |
| PUT | `/routes/{id}/complete` | Complete a route |
| GET | `/routes/{id}/assignments` | Get all assignments for a route |
| PUT | `/assignments/{id}/complete` | Complete a pickup assignment |
| GET | `/assignments/pending` | Get all pending assignments |

## WebSocket
Connect to the WebSocket at:
```
ws://localhost:8081/ws
```
Subscribe to `/topic/vehicle-locations` to receive live vehicle location updates every 5 seconds.

## RabbitMQ Integration
### Queues/Exchanges
- **Exchange**: `waste.management.exchange` (topic exchange)
- **Consumes from**: `pickup.requested.queue` (routing key: `pickup.requested`)
- **Publishes to**: `pickup.completed.queue` (routing key: `pickup.completed`)

## Running the Service
```bash
cd LogisticsService
./mvnw spring-boot:run
```
