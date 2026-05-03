package service;

import model.User;
import repository.AuditLogRepository;
import repository.UserRepository;

public class AuthenticationService {
    private final UserRepository users;
    private final AuditLogRepository auditLog;

    public AuthenticationService(UserRepository users, AuditLogRepository auditLog) {
        this.users = users;
        this.auditLog = auditLog;
    }

    public String login(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            auditLog.record("anonymous", "LOGIN", "Rejected empty username or password");
            return "Please enter both username and password";
        }

        User user = users.findByUsername(username).orElse(null);
        if (user != null && user.matchesPassword(password)) {
            auditLog.record(username, "LOGIN", "Login successful");
            return "Login successful";
        }

        auditLog.record(username, "LOGIN", "Invalid username or password");
        return "Invalid username or password";
    }
}
