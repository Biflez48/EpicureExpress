package com.example.epicureexpress.servlets;

import com.example.epicureexpress.repositories.NomenclaturesRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "imgServlet", urlPatterns = "/product/imgServlet")
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final NomenclaturesRepository nomenclaturesRepository;
    public ImageServlet(
            NomenclaturesRepository nomenclaturesRepository
    ){
        this.nomenclaturesRepository = nomenclaturesRepository;
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        response.setContentType("image/jpeg");
        ServletOutputStream out;
        out = response.getOutputStream();
        byte[] img = nomenclaturesRepository.getImgById(id);
        
        out.write(img);
        out.close();
    }
}
