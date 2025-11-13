package edu.upc.dsa;

import edu.upc.dsa.models.User;

public interface UserManager {

    void addUser(String username, String password);   // per crear usuaris de prova
    User getUser(String username);                    // per obtenir un usuari
    boolean validateUser(String username, String password); // per comprovar el login

    int size();   // opcional però útil per tests
    void clear(); // per buidar usuaris (tests)
}
