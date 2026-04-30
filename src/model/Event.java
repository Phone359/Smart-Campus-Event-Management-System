public class Event {
    private String title;
    private int capacity;
    private int registeredCount;

    public Event(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.registeredCount = 0;
    }

    public boolean isFull() {
        return registeredCount >= capacity;
    }

    public void register() {
        if (!isFull()) {
            registeredCount++;
        }
    }

    public String getTitle() {
        return title;
    }
}
