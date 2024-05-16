package com.example.epicureexpress.servlets;

import com.example.epicureexpress.models.Nomenclature;
import com.example.epicureexpress.repositories.NomenclaturesRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

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
        List<Nomenclature> nomenclatures = nomenclaturesRepository.findNomenclature();
        int number = 0;
        for (int i=0;i<nomenclatures.size();i++) {
            if(nomenclatures.get(i).getId() == id){
                number = i;
                break;
            }
        }
        out.write(nomenclatures.get(number).getImage());
        out.close();
    }
}
