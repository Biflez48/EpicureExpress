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
    public void getNavbar(Model model){
        List<Category> categories = categoriesRepository.findAllCategories();
        model.addAttribute("categories", categories);

        String username = loggedUserManagementService.getUsername();
        String userRole = loggedUserManagementService.getRoleName();

        if(username == null){
            model.addAttribute("authorizeForm", "loginbth");
        }else{
            model.addAttribute("authorizeForm", "logoutform");
            model.addAttribute("adminButton", userRole);
        }
    }
}
