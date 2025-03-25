package iset.traingpsbackend.controller;

import iset.traingpsbackend.model.Locate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class LocateController {
    private static final Logger logger = LoggerFactory.getLogger(LocateController.class);

    private final SimpMessagingTemplate messagingTemplate;

    public LocateController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/locate")
    @SendTo("/topic/location")
    public Locate handleLocation(Locate location) {
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
        return jsonMessage;
    }

    public void sendLocationUpdate(Locate location) {
        logger.info("Sending location update: {}", location);
        messagingTemplate.convertAndSend("/topic/locate", location);
    }
}
