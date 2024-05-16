package com.example.epicureexpress.initializers;

import com.example.epicureexpress.configs.WebMvcConfigure;
import com.example.epicureexpress.repositories.NomenclaturesRepository;
import com.example.epicureexpress.servlets.ImageServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class WebAppInitializer  implements WebApplicationInitializer {
    private final NomenclaturesRepository nomenclaturesRepository;

    public WebAppInitializer(NomenclaturesRepository nomenclaturesRepository) {
        this.nomenclaturesRepository = nomenclaturesRepository;
    }

    public void onStartup(ServletContext container) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebMvcConfigure.class);
        ctx.setServletContext(container);

        ServletRegistration.Dynamic servlet = container.addServlet("imgServlet", new ImageServlet(nomenclaturesRepository));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }
}
