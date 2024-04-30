package com.example.epicureexpress.controllers;

import com.example.epicureexpress.services.LoggedUserManagementService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
            @RequestParam(required = false) String Logout,
            Model model
    ){
        if (Logout != null){
            loggedUserManagementService.setUsername(null);
        }

        String username = loggedUserManagementService.getUsername();
/*
        if (username == null){
            return "redirect:/";
        }
*/
        if(username == null){
            model.addAttribute("autorizeForm",
                    "<button id=\"login-btn\" class=\"btn login-btn\">Вход</button>"
            );
            model.addAttribute("loginForm",
                    "<div id=\"login-overlay\" style=\"display: none;\">\n" +
                            "        <div id=\"login-popup\">\n" +
                            "            <form action=\"/loginconfirm\" method=\"post\">\n" +
                            "                <div>\n" +
                            "                    <label>Логин:</label>\n" +
                            "                    <input type=\"text\" name=\"login\" />\n" +
                            "                </div>\n" +
                            "                <div>\n" +
                            "                    <label>Пароль:</label>\n" +
                            "                    <input type=\"password\" name=\"password\" />\n" +
                            "                </div>\n" +
                            "                <div>\n" +
                            "                    <button type=\"submit\" class=\"btn\">Войти</button>\n" +
                            "                    <button id=\"reg-btn\" type=\"button\" class=\"btn\">Регистрация</button>\n" +
                            "                </div>\n" +
                            "                <button id=\"cancel-log-btn\" type=\"button\" class=\"btn\">Отмена</button>\n" +
                            "            </form>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "\n" +
                            "    <div id=\"register-overlay\" style=\"display: none;\">\n" +
                            "        <div id=\"register-popup\">\n" +
                            "            <form action=\"/registerconfirm\" method=\"post\">\n" +
                            "                <div>\n" +
                            "                    <label>Логин:</label>\n" +
                            "                    <input type=\"text\" name=\"login\" />\n" +
                            "                </div>\n" +
                            "                <div>\n" +
                            "                    <label>Пароль:</label>\n" +
                            "                    <input type=\"password\" name=\"password\" />\n" +
                            "                </div>\n" +
                            "                <div>\n" +
                            "                    <label>Повтор пароля:</label>\n" +
                            "                    <input type=\"password\" name=\"confirm-password\" />\n" +
                            "                </div>\n" +
                            "                <div>\n" +
                            "                    <button type=\"submit\" class=\"btn\">Зарегистрироваться</button>\n" +
                            "                    <button id=\"login-reg-btn\" type=\"button\" class=\"btn\">Уже есть аккаунт</button>\n" +
                            "                </div>\n" +
                            "                <button id=\"cancel-reg-btn\" type=\"button\" class=\"btn\">Отмена</button>\n" +
                            "            </form>\n" +
                            "        </div>\n" +
                            "    </div>"
            );
        }else{
            model.addAttribute("autorizeForm",
                    "<form action=\"/logoutconfirm\" method=\"post\"><button id=\"logout-btn\" type=\"submit\" class=\"btn logout-btn\">Выход</button></form>"
            );
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
