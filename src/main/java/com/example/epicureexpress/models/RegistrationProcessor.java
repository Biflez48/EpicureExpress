package com.example.epicureexpress.models;

import com.example.epicureexpress.repositories.UsersRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Component
@RequestScope
public class RegistrationProcessor {
    private final UsersRepository usersRepository;
    private String username;
    private String password;
    private String repeatPassword;

    public RegistrationProcessor(
            UsersRepository usersRepository
    ){
        this.usersRepository = usersRepository;
    }

    public boolean registration(){
        String username = this.username;
        String password = this.password;
        String repeatPassword = this.repeatPassword;

        List<User> gettingUsers = usersRepository.findUser(username,password);
        if(
                gettingUsers.size() != 0
                || !repeatPassword.equals(password)
                || username.length() < 1
                || password.length() < 6
        ){
            return false;
        }

        usersRepository.addUser(username,password);

        return true;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getRepeatPassword(){
        return repeatPassword;
    }
    public void setRepeatPassword(String repeatPassword){
        this.repeatPassword = repeatPassword;
    }
}
