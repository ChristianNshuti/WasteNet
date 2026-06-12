package com.FacilitiesService.listener;

import com.FacilitiesService.config.RabbitMQConfig;
import com.FacilitiesService.dto.PickupCompletedEvent;
import com.FacilitiesService.ecopoint.EcoPoint;
import com.FacilitiesService.ecopoint.EcoPointService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PickupCompletedListener {

    @Autowired
    private EcoPointService ecoPointService;

    @RabbitListener(queues = RabbitMQConfig.PICKUP_COMPLETED_QUEUE)
    public void handlePickupCompleted(PickupCompletedEvent event) {
        System.out.println("Received Pickup Completed Event: " + event);

        // Calculate points: 10 points per kg of waste, recycled waste gives double points
        int points = calculatePoints(event.getWasteType(), event.getWeight());

        // Create and save EcoPoint
        EcoPoint ecoPoint = new EcoPoint();
        ecoPoint.setCitizenId(event.getCitizenId());
        ecoPoint.setPickupRequestId(event.getRequestId());
        ecoPoint.setPoints(points);

        ecoPointService.createEcoPoint(ecoPoint);

        System.out.println("Awarded " + points + " eco points to citizen " + event.getCitizenId());
    }

    private int calculatePoints(String wasteType, Double weight) {
        int basePoints = (int) (weight * 10);
        if ("RECYCLABLE".equalsIgnoreCase(wasteType) || "ORGANIC".equalsIgnoreCase(wasteType)) {
            return basePoints * 2;
        }
        return basePoints;
    }
}
