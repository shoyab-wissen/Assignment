package com.banking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.banking.helper.DatabaseHandler;
import com.banking.model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class AppController {
    @RequestMapping("/")
    public String home(HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "index.jsp";
        }
        return "dashboard.jsp";
    }

    @RequestMapping("/login")
    public String login() {
        return "login.jsp";
    }

    @RequestMapping("/register")
    public String register() {
        return "register.jsp";
    }

    @RequestMapping("/login-request")
    public String loginRequest(String username, String password, HttpSession session) {
        DatabaseHandler db = new DatabaseHandler();
        User user = db.login(username, password);
        if (user != null) {
            session.setAttribute("user", user.getUsername());
            return "dashboard.jsp";
        }
        return "login.jsp";
    }

    @RequestMapping("/register-request")
    public String registerRequest(String username, String password, String confirmPassword) {
    	if(username == null || password == null || confirmPassword == null) {
    		return "register.jsp";
    	}
    	if(!password.equals(confirmPassword)) {
    		return "register.jsp";
    	}
        DatabaseHandler db = new DatabaseHandler();
        if (db.register(username, password)) {
            return "login.jsp";
        }
        return "register.jsp";
    }
}
