package com.example.projekt;

// Klasa UserManager (Singleton)
public class UserManager {
    private static UserManager instance;
    private LoggedInUser loggedInUser;
    private LoggedInEmployee loggedInEmployee;

    private UserManager() {
        // Private constructor to prevent direct instantiation
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public void setLoggedInUser(LoggedInUser user) {
        loggedInUser = user;
    }

    public LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInEmployee(LoggedInEmployee employee) {
        loggedInEmployee = employee;
    }

    public LoggedInEmployee getLoggedInEmployee() {
        return loggedInEmployee;
    }
}
