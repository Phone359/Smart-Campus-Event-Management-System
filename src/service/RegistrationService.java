package service;

import model.User;
import model.Event;
import model.Registration;

import java.util.ArrayList;
import java.util.List;

public class RegistrationService {
    private List<Registration> registrations = new ArrayList<>();

    public boolean registerForEvent(User user, Event event) {
        if (user == null || event == null) {
            System.out.println("Registration failed. User or event information is missing.");
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
        for (Registration registration : registrations) {
            if (registration.getUsername().equals(user.getUsername()) &&
                registration.getEventTitle().equals(event.getTitle())) {
                return true;
            }
        }
        return false;
    }
} 