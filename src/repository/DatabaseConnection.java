public class DatabaseConnection {

    private static DatabaseConnection instance;

    private DatabaseConnection() {
        // simulate database connection
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public void connect() {
        System.out.println("Database connected.");
    }
}
