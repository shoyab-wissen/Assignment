package com.banking.helper;

import java.sql.Connection;
import java.sql.DriverManager;

import com.banking.model.User;

public class DatabaseHandler {
    public User login(String username, String password) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/empdb", "postgres",
                "tiger");
                var stmt = conn.createStatement()) {
            var rs = stmt.executeQuery(
                    "SELECT * FROM user_details WHERE username = '" + username + "' AND passwd = '" + password + "'");
            if (rs.next()) {
                return new User(rs.getString("username"), rs.getString("passwd"), rs.getDouble("amount"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean register(String username, String password) {
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/empdb", "postgres",
                "tiger");
                var stmt = conn.createStatement()) {
           
            int affected = stmt.executeUpdate(
                    "INSERT INTO user_details (usernam"
                    + ""
                    + ""
                    + "e, passwd, amount) VALUES ('" + username + "', '" + password
                            + "', 0)");
            System.out.println(affected);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
