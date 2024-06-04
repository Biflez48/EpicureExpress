package com.example.epicureexpress.controllers;

import com.example.epicureexpress.services.LoggedUserManagementService;
import com.example.epicureexpress.services.NavbarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {
    private final NavbarService navbarService;
    private final LoggedUserManagementService loggedUserManagementService;

    public MainPageController(
            NavbarService navbarService,
            LoggedUserManagementService loggedUserManagementService
    ){
        this.navbarService = navbarService;
        this.loggedUserManagementService = loggedUserManagementService;
    }

    @GetMapping("/")
    public String mainGet(
            Model model
    ){
        navbarService.getNavbar(model);
        String userRole = loggedUserManagementService.getRoleName();
        if(userRole != null && userRole.equals("courier")){
            return "redirect:/courier";
        }
        return "main.html";
    }

    @RequestMapping("/{address}")
    public String notFind(
            @PathVariable String address,
            Model model) {
        navbarService.getNavbar(model);
        model.addAttribute("address", address);
        return "notfind.html";
    }
}
