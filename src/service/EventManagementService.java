package service;

import model.Event;
import model.User;
import repository.AuditLogRepository;
import repository.EventRepository;

public class EventManagementService {
    private final EventRepository events;
    private final AuditLogRepository auditLog;
    private final AuthorizationService authorization;

    public EventManagementService(EventRepository events, AuditLogRepository auditLog, AuthorizationService authorization) {
        this.events = events;
        this.auditLog = auditLog;
        this.authorization = authorization;
    }

    public String createEvent(User actor, String title, int capacity) {
        if (!authorization.canCreateEvent(actor)) {
            auditLog.record(actorName(actor), "CREATE_EVENT", "Access denied");
            return "Access denied: only admins or organizers can create events";
        }
        if (events.findByTitle(title).isPresent()) {
            auditLog.record(actor.getUsername(), "CREATE_EVENT", "Duplicate event title");
            return "Event already exists";
        }

        events.save(new Event(title, capacity));
        auditLog.record(actor.getUsername(), "CREATE_EVENT", "Event created: " + title);
        return "Event created successfully";
    }

    private String actorName(User actor) {
        return actor == null ? "anonymous" : actor.getUsername();
    }
}
