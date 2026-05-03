package service;

import model.Event;
import model.User;
import repository.AuditLogRepository;

public class RegistrationService {
    private final AuditLogRepository auditLog;
    private final AuthorizationService authorization;

    public RegistrationService(AuditLogRepository auditLog, AuthorizationService authorization) {
        this.auditLog = auditLog;
        this.authorization = authorization;
    }

    public String register(User user, Event event) {
        if (!authorization.canRegisterForEvent(user)) {
            auditLog.record(actorName(user), "REGISTER_EVENT", "Access denied");
            return "Access denied: only students can register for events";
        }
        if (event == null) {
            auditLog.record(user.getUsername(), "REGISTER_EVENT", "Event not found");
            return "Event not found";
        }
        if (event.isRegistered(user)) {
            auditLog.record(user.getUsername(), "REGISTER_EVENT", "Duplicate registration");
            return "You are already registered for this event";
        }
        if (event.isFull()) {
            auditLog.record(user.getUsername(), "REGISTER_EVENT", "Event full");
            return "This event is already full. Please choose another event";
        }

        event.register(user);
        auditLog.record(user.getUsername(), "REGISTER_EVENT", "Registration successful: " + event.getTitle());
        return "Registration successful";
    }

    private String actorName(User user) {
        return user == null ? "anonymous" : user.getUsername();
    }
}
