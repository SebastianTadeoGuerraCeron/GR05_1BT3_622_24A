package com.example.gr05_1bt3_622_24a.servlets;

import dao.RecetaJpaController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EliminarRecetaServlet")
public class EliminarRecetaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RecetaJpaController recetaJpaController = new RecetaJpaController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idRecetaParam = request.getParameter("idReceta");

        try {
            // Convertir el parámetro de ID a Long
            Long idReceta = Long.parseLong(idRecetaParam);

            // Eliminar la receta usando el JPA Controller
            recetaJpaController.destroy(idReceta);

            // Redirigir al foro de recetas después de eliminar
            response.sendRedirect("RecetaServlet");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al eliminar la receta.");
            request.getRequestDispatcher("/foroReceta.jsp").forward(request, response);
        }
    }
}