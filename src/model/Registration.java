package model;

public class Registration {
    private String username;
    private String eventTitle;

    public Registration(String username, String eventTitle) {
        this.username = username;
        this.eventTitle = eventTitle;
    }

    public String getUsername() {
        return username;
    }

    public String getEventTitle() {
        return eventTitle;
    }
}