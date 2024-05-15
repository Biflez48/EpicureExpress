package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.Category;
import com.example.epicureexpress.repositories.CategoriesRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.PreparedStatement;
import java.util.List;

@Controller
public class MainPageController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final CategoriesRepository categoriesRepository;

    public MainPageController(
            LoggedUserManagementService loggedUserManagementService,
            CategoriesRepository categoriesRepository
    ){
        this.loggedUserManagementService = loggedUserManagementService;
        this.categoriesRepository = categoriesRepository;
    }

    @GetMapping("/")
    public String mainGet(
            @RequestParam(required = false) String logsuccess,
            @RequestParam(required = false) String registersuccess,
            Model model
    ){
        List<Category> categories = categoriesRepository.findAllCategories();
        model.addAttribute("categories", categories);

        if (logsuccess != null){
            model.addAttribute("typeFormLog", "exceptauth.html");
            model.addAttribute("typeFormReg", "authorization.html");
        }else{
            if (registersuccess != null){
                model.addAttribute("typeFormLog", "authorization.html");
                model.addAttribute("typeFormReg", "exceptauth.html");
            }else{
                model.addAttribute("typeFormLog", "authorization.html");
                model.addAttribute("typeFormReg", "authorization.html");
            }
        }

        String username = loggedUserManagementService.getUsername();

        if(username == null){
            model.addAttribute("authorizeForm", "loginbth");
        }else{
            model.addAttribute("authorizeForm", "logoutform");
            // вывести кнопку со ссылкой на корзину
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
