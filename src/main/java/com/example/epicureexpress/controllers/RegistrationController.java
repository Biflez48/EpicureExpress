package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.RegistrationProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {
    private final RegistrationProcessor registrationProcessor;

    public RegistrationController(
            RegistrationProcessor registrationProcessor
    ){
        this.registrationProcessor = registrationProcessor;
    }

    @PostMapping("/registerconfirm")
    public String loginPost(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String repeatPassword
    ){
        registrationProcessor.setUsername(login);
        registrationProcessor.setPassword(password);
        registrationProcessor.setRepeatPassword(repeatPassword);
        boolean regged = registrationProcessor.registration();

        if (!regged) {
            return "redirect:/?registersuccess=false";
        }

        return "redirect:/";
    }
}
