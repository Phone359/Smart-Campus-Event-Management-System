package controller;

import model.User;
import service.EventManagementService;

public class EventController {
    private final EventManagementService eventManagementService;

    public EventController(EventManagementService eventManagementService) {
        this.eventManagementService = eventManagementService;
    }

    public String createEvent(User actor, String title, int capacity) {
        String result = eventManagementService.createEvent(actor, title, capacity);
        System.out.println(result);
        return result;
    }
}
