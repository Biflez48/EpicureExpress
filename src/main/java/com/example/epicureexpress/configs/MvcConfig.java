package com.example.epicureexpress.configs;

import com.example.epicureexpress.initializers.WebAppInitializer;
import com.example.epicureexpress.repositories.NomenclaturesRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("/static/","classpath:/static/");
    }

    @Bean
    public WebApplicationInitializer webApplicationInitializer(NomenclaturesRepository nomenclaturesRepository) {
        return new WebAppInitializer(nomenclaturesRepository);
    }
}
