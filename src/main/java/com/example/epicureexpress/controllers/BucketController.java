package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.Category;
import com.example.epicureexpress.repositories.CategoriesRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BucketController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final CategoriesRepository categoriesRepository;

    public BucketController(
            LoggedUserManagementService loggedUserManagementService,
            CategoriesRepository categoriesRepository
    ){
        this.loggedUserManagementService = loggedUserManagementService;
        this.categoriesRepository = categoriesRepository;
    }

    @GetMapping("/bucket")
    public String bucketGet(Model model){
        List<Category> categories = categoriesRepository.findAllCategories();
        model.addAttribute("categories", categories);

        String username = loggedUserManagementService.getUsername();

        if(username == null){
            return "redirect:/";
        }else{
            model.addAttribute("authorizeForm", "logoutform");
        }

        return "bucket.html";
    }
}
