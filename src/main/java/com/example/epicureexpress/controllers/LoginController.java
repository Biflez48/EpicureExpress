package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.LoginProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    private final LoginProcessor loginProcessor;

    public LoginController(LoginProcessor loginProcessor) {
        this.loginProcessor = loginProcessor;
    }

    @PostMapping("/loginconfirm")
    public ResponseEntity<Map<String, Object>> loginPost(
            @RequestParam String login,
            @RequestParam String password
    ){
        loginProcessor.setUsername(login);
        loginProcessor.setPassword(password);
        boolean loggedIn = loginProcessor.login();
        Map<String, Object> response = new HashMap<>();
        response.put("loggedIn", loggedIn);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, Object>> logoutPost(){
        boolean loggedOut = loginProcessor.logout();
        Map<String, Object> response = new HashMap<>();
        response.put("loggedOut", loggedOut);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cancelauth")
    public ResponseEntity<Map<String, Object>> cancelPost(){
        Map<String, Object> response = new HashMap<>();
        response.put("cancelled", true);
        return ResponseEntity.ok(response);
    }
}
