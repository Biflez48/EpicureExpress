package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.Bucket;
import com.example.epicureexpress.models.Category;
import com.example.epicureexpress.repositories.BucketRepository;
import com.example.epicureexpress.repositories.CategoriesRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import com.example.epicureexpress.services.NavbarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BucketController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final NavbarService navbarService;
    private final BucketRepository bucketRepository;

    public BucketController(
            LoggedUserManagementService loggedUserManagementService,
            NavbarService navbarService,
            BucketRepository bucketRepository
    ){
        this.loggedUserManagementService = loggedUserManagementService;
        this.navbarService = navbarService;
        this.bucketRepository = bucketRepository;
    }

    @GetMapping("/bucket")
    public String bucketGet(
            @RequestParam(required = false) String logsuccess,
            @RequestParam(required = false) String registersuccess,
            Model model
    ){

        String username = loggedUserManagementService.getUsername();

        if(username == null){
            return "redirect:/";
        }else{
            navbarService.getNavbar(model,"/bucket",logsuccess,registersuccess);
            model.addAttribute("authorizeForm", "logoutform");
        }

        List<Bucket> products = bucketRepository.findNomenclatures();
        model.addAttribute("productsView",products);

        return "bucket.html";
    }
}
