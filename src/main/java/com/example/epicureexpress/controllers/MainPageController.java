package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.Category;
import com.example.epicureexpress.repositories.CategoriesRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import com.example.epicureexpress.services.NavbarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainPageController {

    private final NavbarService navbarService;

    public MainPageController(
            NavbarService navbarService
    ){
        this.navbarService = navbarService;
    }

    @GetMapping("/")
    public String mainGet(
            @RequestParam(required = false) String logsuccess,
            @RequestParam(required = false) String registersuccess,
            Model model
    ){
        navbarService.getNavbar(model,"/",logsuccess,registersuccess);

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
