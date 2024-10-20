package com.example.gr05_1bt3_622_24a.servlets;

import dao.RecetaJpaController;
import modelo.Receta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ReactionRecetaServlet")
public class ReactionRecetaServlet extends HttpServlet {

    private RecetaJpaController recetaController = new RecetaJpaController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");  // 'like' o 'dislike'
        Long recetaId = Long.parseLong(request.getParameter("recetaId"));

        try {
            // Buscar la receta
            Receta receta = recetaController.findReceta(recetaId);
            if (receta == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Receta no encontrada");
                return;
            }

            // Actualizar la reacción (like o dislike)
            if ("like".equals(action)) {
                receta.aumentarLikes();
            } else if ("dislike".equals(action)) {
                receta.aumentarDislikes();
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
                return;
            }

            // Editar la receta con los nuevos valores
            recetaController.edit(receta);

            // Cargar las recetas actualizadas
            List<Receta> listaRecetas = recetaController.findRecetaEntities();
            request.setAttribute("listaRecetas", listaRecetas);

            // Redirigir a foroReceta.jsp para mostrar las recetas actualizadas
            request.getRequestDispatcher("/foroReceta.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la reacción");
        }
    }
}
