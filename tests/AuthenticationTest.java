import controller.AuthenticationController;
import model.User;

public class AuthenticationTest {
    public static void main(String[] args) {
        AuthenticationController auth = new AuthenticationController();

        User validUser = new User("admin", "1234", "ADMIN");

        System.out.println("Test 1: Happy Path Login");
        assertEquals("Login successful", auth.login(validUser, "admin", "1234"));

        System.out.println("Test 2: Negative Login - Wrong Password");
        assertEquals("Invalid username or password", auth.login(validUser, "admin", "wrong"));

        System.out.println("Test 3: Boundary Login - Empty Username");
        assertEquals("Please enter both username and password", auth.login(validUser, "", "1234"));

        System.out.println("Authentication tests passed");
    }

    private static void assertEquals(String expected, String actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + " but got: " + actual);
        }
    }
}
