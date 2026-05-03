import controller.RegistrationController;
import model.Event;
import model.User;

public class EventRegistrationTest {
    public static void main(String[] args) {
        User student = new User("student1", "pass", "STUDENT");
        Event event = new Event("Tech Workshop", 1);

        RegistrationController controller = new RegistrationController();

        System.out.println("Test 1: Happy Path Registration");
        assertEquals("Registration successful", controller.register(student, event));

        System.out.println("Test 2: Negative Case - Duplicate Registration");
        assertEquals("You are already registered for this event", controller.register(student, event));

        System.out.println("Test 3: Boundary Case - Full Event");
        User student2 = new User("student2", "pass", "STUDENT");
        assertEquals("This event is already full. Please choose another event", controller.register(student2, event));

        System.out.println("Test 4: RBAC Negative Case - Admin Cannot Register");
        User admin = new User("admin", "1234", "ADMIN");
        Event secondEvent = new Event("Research Forum", 5);
        assertEquals("Access denied: only students can register for events", controller.register(admin, secondEvent));

        System.out.println("Event registration tests passed");
    }

    private static void assertEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + " but got: " + actual);
        }
    }
}
