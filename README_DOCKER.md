# Waste Management Project - Docker Setup

This project uses Docker Compose to orchestrate all microservices and dependencies.

## Services Included:
- PostgreSQL 16 (Database)
- RabbitMQ 3 (Message Broker with Management UI)
- User Service (Port 8080)
- Logistics Service (Port 8081)
- Facilities Service (Port 8082)

## How to Run:

1. Make sure Docker and Docker Compose are installed on your system
2. Navigate to the `backend` directory
3. Run:
   ```bash
   docker-compose up --build
   ```
4. Wait for all services to start (it might take a few minutes on first build)

## Access Points:
- User Service API: http://localhost:8080
- Logistics Service API: http://localhost:8081
- Facilities Service API: http://localhost:8082
- RabbitMQ Management UI: http://localhost:15672 (Username: guest, Password: guest)

## How to Stop:
Press `Ctrl+C` or run:
```bash
docker-compose down
```

To stop and remove volumes (deletes all data):
```bash
docker-compose down -v
```
