package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.Category;
import com.example.epicureexpress.models.Nomenclature;
import com.example.epicureexpress.repositories.CategoriesRepository;
import com.example.epicureexpress.repositories.NomenclaturesRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class AdministrationController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final CategoriesRepository categoriesRepository;
    private final NomenclaturesRepository nomenclaturesRepository;

    public AdministrationController(
            LoggedUserManagementService loggedUserManagementService,
            CategoriesRepository categoriesRepository,
            NomenclaturesRepository nomenclaturesRepository
    ){
        this.loggedUserManagementService = loggedUserManagementService;
        this.categoriesRepository = categoriesRepository;
        this.nomenclaturesRepository = nomenclaturesRepository;
    }

    @GetMapping("/administrate")
    public String administrateGet(
            Model model
    ){
        List<Category> categories = categoriesRepository.findAllCategories();
        model.addAttribute("categories", categories);

        String username = loggedUserManagementService.getUsername();
        int userRole = loggedUserManagementService.getIdRole();

        if(username == null || userRole != 1){
            return "redirect:/";
        }else{
            model.addAttribute("authorizeForm", "logoutform");
        }

        return "administration.html";
    }

    @PostMapping("/uploadimage")
    public String uploadImagePost(
            @RequestParam("file") MultipartFile file,
            @RequestParam String name,
            @RequestParam String price
    ) throws IOException {

        Nomenclature nomenclature = new Nomenclature();
        nomenclature.setImage(file.getBytes());
        nomenclature.setName(name);
        nomenclature.setPrice(new BigDecimal(price));
        nomenclature.setCountPurchase(0);
        nomenclature.setIdType(1);

        nomenclaturesRepository.addNomenclature(nomenclature);

        return "redirect:/administrate";
    }
}
