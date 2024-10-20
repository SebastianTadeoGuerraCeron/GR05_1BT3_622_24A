package com.example.gr05_1bt3_622_24a.servlets;

import dao.ComentarioRecetaJpaController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/EliminarComentarioRecetaServlet")
public class EliminarComentarioRecetaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ComentarioRecetaJpaController comentarioRecetaJpaController = new ComentarioRecetaJpaController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idComentarioParam = request.getParameter("idComentario");
        String idRecetaParam = request.getParameter("idReceta");

        try {
            // Convertir el parámetro de ID a Long
            Long idComentario = Long.parseLong(idComentarioParam);

            // Eliminar el comentario usando el JPA Controller
            comentarioRecetaJpaController.destroy(idComentario);

            // Redirigir a la página de la receta después de eliminar el comentario
            response.sendRedirect("AmpliarRecetaServlet?id=" + idRecetaParam);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al eliminar el comentario.");
            request.getRequestDispatcher("/ampliarReceta.jsp").forward(request, response);
        }
    }
}
