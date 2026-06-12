package com.waste.pickup;

import com.waste.config.RabbitMQConfig;
import com.waste.pickup.dto.CreatePickupRequest;
import com.waste.pickup.dto.PickupRequestedEvent;
import com.waste.user.User;
import com.waste.user.UserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PickupRequestService {

    @Autowired
    private PickupRequestRepository pickupRequestRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public PickupRequest createPickupRequest(CreatePickupRequest request) {
        // Get the authenticated citizen from security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User citizen = userService.getUserByEmail(email);

        // Create and save the pickup request
        PickupRequest pickupRequest = new PickupRequest();
        pickupRequest.setCitizenId(citizen.getId());
        pickupRequest.setWasteType(WasteType.valueOf(request.getWasteType()));
        pickupRequest.setLatitude(request.getLatitude());
        pickupRequest.setLongitude(request.getLongitude());
        pickupRequest.setStatus(PickupStatus.PENDING);
        PickupRequest savedRequest = pickupRequestRepository.save(pickupRequest);

        // Publish PickupRequestedEvent to RabbitMQ exchange for Developer 2 to consume
        PickupRequestedEvent event = new PickupRequestedEvent(
                savedRequest.getId(),
                savedRequest.getCitizenId(),
                savedRequest.getWasteType().name(),
                savedRequest.getLatitude(),
                savedRequest.getLongitude(),
                savedRequest.getStatus().name(),
                savedRequest.getCreatedAt()
        );
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY,
                event
        );

        return savedRequest;
    }
}
