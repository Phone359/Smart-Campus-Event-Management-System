import controllers.RegistrationController;
import models.Event;
import models.User;

public class EventRegistrationTest {
    public static void main(String[] args) {
        User student = new User("student1", "pass", "STUDENT");
        Event event = new Event("Tech Workshop", 1);

        RegistrationController controller = new RegistrationController();

        System.out.println("Test 1: Happy Path Registration");
        controller.register(student, event);

        System.out.println("Test 2: Negative Case - Duplicate Registration");
        controller.register(student, event);

        System.out.println("Test 3: Boundary Case - Full Event");
        User student2 = new User("student2", "pass", "STUDENT");
        controller.register(student2, event);
    }
}
