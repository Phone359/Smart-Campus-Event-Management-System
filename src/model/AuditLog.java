public class AuditLog {
    private String username;
    private String action;
    private String timestamp;

    public AuditLog(String username, String action, String timestamp) {
        this.username = username;
        this.action = action;
        this.timestamp = timestamp;
    }

    public void log() {
        System.out.println("User: " + username + " | Action: " + action + " | Time: " + timestamp);
    }
}
