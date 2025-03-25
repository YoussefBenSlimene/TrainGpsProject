package iset.traingpsbackend.controller;

import iset.traingpsbackend.model.Locate;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
public class LocateController {
    private static final Logger logger = LoggerFactory.getLogger(LocateController.class);

    @MessageMapping("/locate")
    @SendTo("/topic/locate")
    public Locate locate(Locate location) throws Exception {
        Thread.sleep(1000);
        return location;
    }

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public String handleMessage(String message) {
        logger.info("Received message: {}", message);
        return "Server received: " + message;
    }
}
