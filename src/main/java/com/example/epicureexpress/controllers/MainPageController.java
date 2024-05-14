package com.example.epicureexpress.controllers;

import com.example.epicureexpress.services.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainPageController {

    private final LoggedUserManagementService loggedUserManagementService;

    public MainPageController(
            LoggedUserManagementService loggedUserManagementService
    ){
        this.loggedUserManagementService = loggedUserManagementService;
    }

    @GetMapping("/")
    public String mainGet(
            @RequestParam(required = false) String logsuccess,
            Model model
    ){
        if (logsuccess != null){
            model.addAttribute("typeFormLog", "exceptauth.html");
            model.addAttribute("typeFormReg", "authorization.html");
        }else{
            model.addAttribute("typeFormLog", "authorization.html");
            model.addAttribute("typeFormReg", "authorization.html");
        }

        String username = loggedUserManagementService.getUsername();

        if(username == null){
            model.addAttribute("authorizeForm", "loginbth");
        }else{
            model.addAttribute("authorizeForm", "logoutform");
        }

        return "main.html";
    }

    @RequestMapping("/{address}")
    public String notFind(
            @PathVariable String address,
            Model page) {
        page.addAttribute("address", address);
        return "notfind.html";
    }
}
