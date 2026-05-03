package repository;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import model.User;

public class UserRepository {
    private final Map<String, User> usersByUsername = new LinkedHashMap<>();

    public void save(User user) {
        usersByUsername.put(user.getUsername(), user);
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(usersByUsername.get(username));
    }
}
