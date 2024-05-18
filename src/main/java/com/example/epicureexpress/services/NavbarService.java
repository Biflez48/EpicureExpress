package com.example.epicureexpress.services;

import com.example.epicureexpress.models.Category;
import com.example.epicureexpress.repositories.CategoriesRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
public class NavbarService {

    private final CategoriesRepository categoriesRepository;
    private final LoggedUserManagementService loggedUserManagementService;

    public NavbarService(
            CategoriesRepository categoriesRepository,
            LoggedUserManagementService loggedUserManagementService
    ){
        this.categoriesRepository = categoriesRepository;
        this.loggedUserManagementService = loggedUserManagementService;
    }
    public void getNavbar(Model model, String addressPage){
        List<Category> categories = categoriesRepository.findAllCategories();
        model.addAttribute("categories", categories);

        model.addAttribute("typeFormLog", "authorization.html");
        model.addAttribute("typeFormReg", "authorization.html");
        model.addAttribute("namepage", addressPage);

        String username = loggedUserManagementService.getUsername();

        if(username == null){
            model.addAttribute("authorizeForm", "loginbth");
        }else{
            model.addAttribute("authorizeForm", "logoutform");
        }
    }
}
