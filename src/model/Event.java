package model;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Event {
    private final String title;
    private final int capacity;
    private final Set<String> registeredUsernames = new LinkedHashSet<>();

    public Event(String title, int capacity) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Event title is required");
        }
        if (capacity <= 0) {
            throw new IllegalArgumentException("Event capacity must be greater than zero");
        }
        this.title = title;
        this.capacity = capacity;
    }

    public String getTitle() {
        return title;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRegisteredCount() {
        return registeredUsernames.size();
    }

    public boolean isFull() {
        return getRegisteredCount() >= capacity;
    }

    public boolean isRegistered(User user) {
        return registeredUsernames.contains(user.getUsername());
    }

    public boolean register(User user) {
        if (isFull() || isRegistered(user)) {
            return false;
        }
        return registeredUsernames.add(user.getUsername());
    }

    public Set<String> getRegisteredUsernames() {
        return Collections.unmodifiableSet(registeredUsernames);
    }
}
