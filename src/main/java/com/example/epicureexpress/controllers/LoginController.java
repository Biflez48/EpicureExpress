package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.LoginProcessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private final LoginProcessor loginProcessor;

    public LoginController(LoginProcessor loginProcessor) {
        this.loginProcessor = loginProcessor;
    }

    @PostMapping("/loginconfirm")
    public String loginPost(
            @RequestParam String login,
            @RequestParam String password
    ){
        loginProcessor.setUsername(login);
        loginProcessor.setPassword(password);
        boolean loggedIn = loginProcessor.login();

        if (!loggedIn) {
            return "redirect:/?logsuccess=false";
        }

        return "redirect:/";
    }

    @PostMapping("/logoutconfirm")
    public String logoutPost(){
        boolean loggedOut = loginProcessor.logout();
        return "redirect:/";
    }

    @PostMapping("/cancelauth")
    public String cancelPost(){
        return "redirect:/";
    }

}
