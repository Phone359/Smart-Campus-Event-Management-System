import controller.AuthenticationController;
import model.User; 
public class AuthenticationTest {
    public static void main(String[] args) {
        AuthenticationController auth = new AuthenticationController();

        User validUser = new User("admin", "1234", "ADMIN");

        System.out.println("Test 1: Happy Path Login");
        auth.login(validUser, "admin", "1234");

        System.out.println("Test 2: Negative Login - Wrong Password");
        auth.login(validUser, "admin", "wrong");

        System.out.println("Test 3: Boundary Login - Empty Username");
        auth.login(validUser, "", "1234");
    }
}
