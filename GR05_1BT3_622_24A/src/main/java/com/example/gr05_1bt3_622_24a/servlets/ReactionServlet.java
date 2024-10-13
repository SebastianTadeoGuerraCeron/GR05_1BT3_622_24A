package com.example.gr05_1bt3_622_24a.servlets;

import dao.ResenaJpaController;
import modelo.Resena;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

            // Cargar la lista actualizada de reseñas desde la base de datos
            List<Resena> listaResenas = resenaController.findResenaEntities();

            // Establecer la lista de reseñas como atributo en la solicitud
            request.setAttribute("listaResenas", listaResenas);

            // Redirigir a foro.jsp con la lista actualizada de reseñas
            request.getRequestDispatcher("/foro.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la reacción");
        }
    }
}
