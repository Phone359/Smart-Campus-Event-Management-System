import controller.EventController;
import model.User;
import repository.AuditLogRepository;
import repository.EventRepository;
import service.AuthorizationService;
import service.EventManagementService;

public class EventManagementTest {
    public static void main(String[] args) {
        EventRepository eventRepository = new EventRepository();
        AuditLogRepository auditLog = new AuditLogRepository();
        EventManagementService service = new EventManagementService(
                eventRepository,
                auditLog,
                new AuthorizationService());
        EventController controller = new EventController(service);

        User organizer = new User("organizer1", "pass", "ORGANIZER");
        User student = new User("student1", "pass", "STUDENT");

        System.out.println("Test 1: Organizer Creates Event");
        assertEquals("Event created successfully", controller.createEvent(organizer, "AI Seminar", 30));

        System.out.println("Test 2: Duplicate Event Rejected");
        assertEquals("Event already exists", controller.createEvent(organizer, "AI Seminar", 30));

        System.out.println("Test 3: Student Cannot Create Event");
        assertEquals("Access denied: only admins or organizers can create events",
                controller.createEvent(student, "Student-only Attempt", 20));

        System.out.println("Test 4: Audit Entries Created");
        assertEquals(3, auditLog.findAll().size());

        System.out.println("Event management tests passed");
    }

    private static void assertEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + " but got: " + actual);
        }
    }

    private static void assertEquals(int expected, int actual) {
        if (expected != actual) {
            throw new AssertionError("Expected: " + expected + " but got: " + actual);
        }
    }
}
