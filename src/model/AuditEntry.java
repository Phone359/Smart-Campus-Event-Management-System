package model;

import java.time.LocalDateTime;

public class AuditEntry {
    private final LocalDateTime createdAt;
    private final String actor;
    private final String action;
    private final String result;

    public AuditEntry(String actor, String action, String result) {
        this.createdAt = LocalDateTime.now();
        this.actor = actor;
        this.action = action;
        this.result = result;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getActor() {
        return actor;
    }

    public String getAction() {
        return action;
    }

    public String getResult() {
        return result;
    }

    @Override
    public String toString() {
        return createdAt + " | " + actor + " | " + action + " | " + result;
    }
}
