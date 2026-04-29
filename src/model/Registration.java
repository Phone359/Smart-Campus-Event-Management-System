package model;
import java.time.LocalDateTime;

public class Registration {
    private Long id;
    // private User user;   
    // private Event event; 
    private LocalDateTime registrationDate;


    public Registration() {
        this.registrationDate = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    // public User getUser() { return user; }
    // public void setUser(User user) { this.user = user; }

    // public Event getEvent() { return event; }
    // public void setEvent(Event event) { this.event = event; }

    public LocalDateTime getRegistrationDate() { return registrationDate; }
}