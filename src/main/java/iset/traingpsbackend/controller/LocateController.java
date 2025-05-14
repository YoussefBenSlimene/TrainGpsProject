package iset.traingpsbackend.controller;

import iset.traingpsbackend.model.Locate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class LocateController {
    private static final Logger logger = LoggerFactory.getLogger(LocateController.class);

    private final SimpMessagingTemplate messagingTemplate;
    // Keep track of latest trains positions
    private final Map<String, Locate> trainLocations = new ConcurrentHashMap<>();

    public LocateController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/locate")
    @SendTo("/topic/location")
    public Locate handleLocation(Locate location) {
        // Store the location by train ID if available
        if (location.getTrain() != null && location.getTrain().getId() != null) {
            String trainId = location.getTrain().getId().toString();
            trainLocations.put(trainId, location);
        }

        // Process and return the location
        return location;
    }

    @MessageMapping("/locate-json")
    @SendTo("/topic/locate")
    public String locateJson(@Payload String jsonMessage) {
        if (jsonMessage == null || jsonMessage.isBlank()) {
            logger.error("Received empty JSON message!");
            return "{}";
        }

        logger.info("Received JSON message: {}", jsonMessage);

        // If this is an array, it might be multiple trains
        if (jsonMessage.trim().startsWith("[")) {
            // Also broadcast to the trains topic for better handling of multiple trains
            messagingTemplate.convertAndSend("/topic/trains", jsonMessage);
        }

        return jsonMessage;
    }

    @MessageMapping("/get-all-trains")
    @SendTo("/topic/trains")
    public List<Locate> getAllTrains() {
        return new ArrayList<>(trainLocations.values());
    }

    public void sendLocationUpdate(Locate location) {
        logger.info("Sending location update: {}", location);
        messagingTemplate.convertAndSend("/topic/locate", location);

        // Store the location by train ID if available
        if (location.getTrain() != null && location.getTrain().getId() != null) {
            String trainId = location.getTrain().getId().toString();
            trainLocations.put(trainId, location);
        }
    }
}
