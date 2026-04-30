package app;

import controller.AuthenticationController;
import controller.RegistrationController;
import model.User;
import model.Event;

public class Main {
    public static void main(String[] args) {

        User user = new User("admin", "1234", "ADMIN");

        AuthenticationController auth = new AuthenticationController();
        auth.login(user, "admin", "1234");

        Event event = new Event("Tech Workshop", 1);
        RegistrationController reg = new RegistrationController();

        reg.register(user, event);
    }
}