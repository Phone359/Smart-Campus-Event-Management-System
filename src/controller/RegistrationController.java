package controller;

import model.Event;
import model.User;
import repository.AuditLogRepository;
import service.AuthorizationService;
import service.RegistrationService;

public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController() {
        this.registrationService = new RegistrationService(new AuditLogRepository(), new AuthorizationService());
    }

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public String register(User user, Event event) {
        String result = registrationService.register(user, event);
        System.out.println(result);
        return result;
    }
}
