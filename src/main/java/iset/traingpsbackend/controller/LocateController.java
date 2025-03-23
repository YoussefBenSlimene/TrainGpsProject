package iset.traingpsbackend.controller;

import iset.traingpsbackend.model.Locate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class LocateController {


    @MessageMapping("/locate")
    @SendTo("/topic/locate")
    public Locate locate(Locate location) throws Exception {
        Thread.sleep(1000);
        return location;
    }
}
