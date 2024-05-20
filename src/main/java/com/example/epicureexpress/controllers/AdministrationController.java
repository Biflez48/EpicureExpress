package com.example.epicureexpress.controllers;

import com.example.epicureexpress.models.Nomenclature;
import com.example.epicureexpress.repositories.CategoriesRepository;
import com.example.epicureexpress.repositories.NomenclaturesRepository;
import com.example.epicureexpress.repositories.TypesRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import com.example.epicureexpress.services.NavbarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;

@Controller
public class AdministrationController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final NomenclaturesRepository nomenclaturesRepository;
    private final NavbarService navbarService;
    private final TypesRepository typesRepository;
    private final CategoriesRepository categoriesRepository;

    public AdministrationController(
            LoggedUserManagementService loggedUserManagementService,
            NomenclaturesRepository nomenclaturesRepository,
            NavbarService navbarService,
            TypesRepository typesRepository,
            CategoriesRepository categoriesRepository
    ){
        this.loggedUserManagementService = loggedUserManagementService;
        this.nomenclaturesRepository = nomenclaturesRepository;
        this.navbarService = navbarService;
        this.typesRepository = typesRepository;
        this.categoriesRepository = categoriesRepository;
    }

    @GetMapping("/administrate")
    public String administrateGet(
            Model model
    ){
        int userRole = loggedUserManagementService.getIdRole();

        if(userRole != 1){
            return "redirect:/";
        }else{
            navbarService.getNavbar(model);
            model.addAttribute("authorizeForm", "logoutform");
            model.addAttribute("types", typesRepository.findAllTypes());
            model.addAttribute("categories", categoriesRepository.findAllCategories());
        }

        return "administration.html";
    }

    @PostMapping("/uploadimage")
    public String uploadImagePost(
            @RequestParam("file") MultipartFile file,
            @RequestParam String name,
            @RequestParam String price,
            @RequestParam int type,
            @RequestParam(required = false) String categories
    ) throws IOException {

        Nomenclature nomenclature = new Nomenclature();

        nomenclature.setImage(file.getBytes());
        nomenclature.setName(name);
        nomenclature.setPrice(new BigDecimal(price));
        nomenclature.setIdType(type);

        int newId = nomenclaturesRepository.addNomenclature(nomenclature);

        if (categories != null && !categories.isEmpty()) {
            String[] categoryIds = categories.split(",");
            for (String categoryId : categoryIds) {
                nomenclaturesRepository.addNomenclatureCategory(newId, Integer.parseInt(categoryId));
            }
        }

        return "redirect:/administrate";
    }
}
