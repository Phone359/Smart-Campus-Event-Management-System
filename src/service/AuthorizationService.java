package service;

import model.Role;
import model.User;

public class AuthorizationService {
    public boolean canCreateEvent(User user) {
        return user != null && (user.getRole() == Role.ADMIN || user.getRole() == Role.ORGANIZER);
    }

    public boolean canRegisterForEvent(User user) {
        return user != null && user.getRole() == Role.STUDENT;
    }
}
