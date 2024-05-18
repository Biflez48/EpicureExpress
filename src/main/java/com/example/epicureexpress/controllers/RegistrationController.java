package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.RegistrationProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class RegistrationController {
    private final RegistrationProcessor registrationProcessor;

    public RegistrationController(
            RegistrationProcessor registrationProcessor
    ){
        this.registrationProcessor = registrationProcessor;
    }

    @PostMapping("/registerconfirm")
    public ResponseEntity<Map<String, Object>> registerPost(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String repeatPassword
    ){
        registrationProcessor.setUsername(login);
        registrationProcessor.setPassword(password);
        registrationProcessor.setRepeatPassword(repeatPassword);
        boolean registered = registrationProcessor.registration();

        Map<String, Object> response = new HashMap<>();
        response.put("registered", registered);
        return ResponseEntity.ok(response);
    }
}
