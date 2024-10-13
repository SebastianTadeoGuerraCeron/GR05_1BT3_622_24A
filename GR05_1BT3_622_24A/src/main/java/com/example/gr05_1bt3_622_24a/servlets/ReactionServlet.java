package com.example.gr05_1bt3_622_24a.servlets;


import dao.ResenaJpaController;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Resena;

import java.io.IOException;

@WebServlet("/ReactionServlet")
public class ReactionServlet extends HttpServlet {

    private ResenaJpaController resenaController = new ResenaJpaController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");  // 'like' o 'dislike'
        Long resenaId = Long.parseLong(request.getParameter("resenaId"));

        try {
            // Buscar la reseña
            Resena resena = resenaController.findResena(resenaId);

            // Actualizar los contadores de "Likes" o "Dislikes"
            if ("like".equals(action)) {
                resena.aumentarLikes();  // Incrementa el contador de Likes
            } else if ("dislike".equals(action)) {
                resena.aumentarDislikes();  // Incrementa el contador de Dislikes
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
                return;
            }

            // Guardar los cambios en la base de datos
            resenaController.edit(resena);

            // Redirigir de vuelta al foro para mostrar los cambios
            response.sendRedirect("foro.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la reacción");
        }
    }
}

