package controller;

import model.User;
import model.AuditLog;

public class AuthenticationController {

    public boolean login(User user, String inputUsername, String inputPassword) {

        // Guard Clauses
        if (inputUsername == null || inputPassword == null) {
            System.out.println("Username or password cannot be empty.");
            return false;
        }

        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
            System.out.println("Please enter both username and password.");
            return false;
        }

        // Validation
        if (user.getUsername().equals(inputUsername) &&
            user.getPassword().equals(inputPassword)) {

            if (user.getRole().equals("ADMIN") ||
                user.getRole().equals("STUDENT") ||
                user.getRole().equals("FACULTY")) {

                AuditLog log = new AuditLog(
                    user.getUsername(),
                    "Login Successful",
                    java.time.LocalDateTime.now().toString()
                );
                log.log();

                System.out.println("Login successful.");
                return true;
            } else {
                System.out.println("Access denied. Invalid role.");
                return false;
            }
        }

        System.out.println("Invalid username or password.");
        return false;
    }
}