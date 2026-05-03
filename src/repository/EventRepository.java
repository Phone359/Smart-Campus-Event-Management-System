package repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import model.Event;

public class EventRepository {
    private final Map<String, Event> eventsByTitle = new LinkedHashMap<>();

    public void save(Event event) {
        eventsByTitle.put(event.getTitle(), event);
    }

    public Optional<Event> findByTitle(String title) {
        return Optional.ofNullable(eventsByTitle.get(title));
    }
}
