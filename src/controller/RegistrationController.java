package controller;

import model.User;
import model.Event;
import service.RegistrationService;

public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController() {
        this.registrationService = new RegistrationService();
    }

    public boolean register(User user, Event event) {
        return registrationService.registerForEvent(user, event);
    }
} 