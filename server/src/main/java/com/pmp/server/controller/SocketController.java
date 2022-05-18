package com.pmp.server.controller;

import com.pmp.server.dto.NotificationDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class SocketController {
    @MessageMapping("/chat")
    @SendTo("/topic/tenants")
    public NotificationDTO send(NotificationDTO message) throws Exception {
        return message;
    }

    @MessageMapping("/chat2")
    @SendTo("/topic/landlords")
    public NotificationDTO sendToLandlord(NotificationDTO message) throws Exception {
        return message;
    }
}
