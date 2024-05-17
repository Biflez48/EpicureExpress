package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.LoginProcessor;
import org.springframework.stereotype.Controller;
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
            @RequestParam String password,
            @RequestParam String addresspage
    ){
        loginProcessor.setUsername(login);
        loginProcessor.setPassword(password);
        boolean loggedIn = loginProcessor.login();

        if (!loggedIn) {
            return "redirect:"+addresspage+"?logsuccess=false";
        }

        return "redirect:"+addresspage;
    }

    @PostMapping("/logoutconfirm")
    public String logoutPost(
            @RequestParam String addresspage
    ){
        boolean loggedOut = loginProcessor.logout();
        return "redirect:"+addresspage;
    }

    @PostMapping("/cancelauth")
    public String cancelPost(
            @RequestParam String addresspage
    ){
        return "redirect:"+addresspage;
    }

}
