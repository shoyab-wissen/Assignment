package com.banking.model;

public class User {
    private String username;
    private String password;
    private double amount;

    public User(String username, String password, double amount) {
        this.username = username;
        this.password = password;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getAmount() {
        return amount;
    }
}
