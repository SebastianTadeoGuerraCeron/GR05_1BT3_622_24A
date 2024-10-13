package com.example.gr05_1bt3_622_24a.servlets;

import dao.ResenaJpaController;
import modelo.Resena;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AmpliarResenaServlet")
public class AmpliarResenaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ResenaJpaController resenaJpaController = new ResenaJpaController();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID de la reseña desde el parámetro de la URL
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try {
                Long idResena = Long.parseLong(idParam);

                // Buscar la reseña en la base de datos usando el ID
                Resena resenaAmpliada = resenaJpaController.findResena(idResena);

                if (resenaAmpliada != null) {
                    // Pasar la reseña al JSP
                    request.setAttribute("resena", resenaAmpliada);
                    request.getRequestDispatcher("/verResena.jsp").forward(request, response);
                } else {
                    // Si no se encuentra la reseña, redirigir a foro.jsp con un mensaje de error
                    request.setAttribute("error", "La reseña no se ha encontrado.");
                    request.getRequestDispatcher("/foro.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                // Si el ID no es válido, redirigir a foro.jsp con un mensaje de error
                request.setAttribute("error", "ID de reseña inválido.");
                request.getRequestDispatcher("/foro.jsp").forward(request, response);
            }
        } else {
            // Si no se proporciona ID, redirigir a foro.jsp con un mensaje de error
            request.setAttribute("error", "No se proporcionó un ID de reseña.");
            request.getRequestDispatcher("/foro.jsp").forward(request, response);
        }
    }
}
