package edu.upc.dsa;

import edu.upc.dsa.models.User;

import java.util.HashMap;
import java.util.Map;

public class UserManagerImpl implements UserManager {

    private static UserManager instance;
    private Map<String, User> users;

    // Constructor privat -> patró Singleton
    private UserManagerImpl() {
        users = new HashMap<>();

        // Usuaris de prova
        this.addUser("admin", "1234");
        this.addUser("user", "pass");
    }

    // Obtenir la instància única
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManagerImpl();
        }
        return instance;
    }

    @Override
    public void addUser(String username, String password) {
        users.put(username, new User(username, password));
    }

    @Override
    public User getUser(String username) {
        return users.get(username);
    }

    @Override
    public boolean validateUser(String username, String password) {
        User u = users.get(username);
        if (u == null) return false;
        return u.getPassword().equals(password);
    }

    @Override
    public int size() {
        return users.size();
    }

    @Override
    public void clear() {
        users.clear();
    }
}
