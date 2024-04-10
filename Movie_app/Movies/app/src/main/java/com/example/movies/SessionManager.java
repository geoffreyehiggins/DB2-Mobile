package com.example.movies;

public class SessionManager {
    private static SessionManager instance;
    private String email;
    // private int accountId;

    private SessionManager() {
        // Private constructor to prevent instantiation outside this class.
    }

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

/*    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountId() {
        return accountId;
    }*/
}
