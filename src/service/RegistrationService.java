package service;

import java.util.ArrayList;
import java.util.List;

import model.User;
import model.Event;
import model.Registration;

public class RegistrationService {

    private List<Registration> registrations = new ArrayList<>();

    public boolean registerForEvent(User user, Event event) {

        if (user == null || event == null) {
            System.out.println("Registration failed. Missing information.");
            return false;
        }

        if (event.isFull()) {
            System.out.println("This event is already full. Please choose another event.");
            return false;
        }

        if (isAlreadyRegistered(user, event)) {
            System.out.println("You are already registered for this event.");
            return false;
        }

        event.register();
        registrations.add(new Registration(user.getUsername(), event.getTitle()));

        System.out.println("Registration successful.");
        return true;
    }

    private boolean isAlreadyRegistered(User user, Event event) {
        for (Registration reg : registrations) {
            if (reg.getUsername().equals(user.getUsername()) &&
                reg.getEventTitle().equals(event.getTitle())) {
                return true;
            }
        }
        return false;
    }
}