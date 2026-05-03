package controller;

import model.User;
import repository.AuditLogRepository;
import repository.UserRepository;
import service.AuthenticationService;

public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController() {
        UserRepository users = new UserRepository();
        users.save(new User("admin", "1234", "ADMIN"));
        users.save(new User("student1", "pass", "STUDENT"));
        this.authenticationService = new AuthenticationService(users, new AuditLogRepository());
    }

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public String login(User expectedUser, String username, String password) {
        return login(username, password);
    }

    public String login(String username, String password) {
        String result = authenticationService.login(username, password);
        System.out.println(result);
        return result;
    }
}
