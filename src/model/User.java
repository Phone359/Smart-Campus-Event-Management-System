package model;

import java.util.Objects;

public class User {
    private final String username;
    private final String password;
    private final Role role;

    public User(String username, String password, String role) {
        this(username, password, Role.valueOf(role.toUpperCase()));
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public Role getRole() {
        return role;
    }

    public boolean matchesPassword(String passwordAttempt) {
        return Objects.equals(password, passwordAttempt);
    }
}
