package com.logistics.listener;

import com.logistics.config.RabbitMQConfig;
import com.logistics.dto.PickupRequestedEvent;
import com.logistics.service.RoutingService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PickupRequestedListener {

    private final RoutingService routingService;

    @RabbitListener(queues = RabbitMQConfig.PICKUP_REQUESTED_QUEUE)
    public void handlePickupRequested(PickupRequestedEvent event) {
        routingService.handlePickupRequested(event).subscribe();
    }
}
