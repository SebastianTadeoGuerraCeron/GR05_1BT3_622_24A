package com.example.gr05_1bt3_622_24a.servlets;

import dao.RecetaJpaController;
import modelo.Receta;
import modelo.ComentarioReceta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AmpliarRecetaServlet")
public class AmpliarRecetaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RecetaJpaController recetaJpaController = new RecetaJpaController();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID de la receta desde el parámetro de la URL
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try {
                Long idReceta = Long.parseLong(idParam);

                // Buscar la receta en la base de datos usando el ID
                Receta recetaAmpliada = recetaJpaController.findReceta(idReceta);

                if (recetaAmpliada != null) {
                    // Obtener los comentarios de la receta
                    List<ComentarioReceta> listaComentarios = recetaAmpliada.getComentarios();

                    // Pasar la receta y los comentarios al JSP
                    request.setAttribute("receta", recetaAmpliada);
                    request.setAttribute("listaComentarios", listaComentarios);

                    // Redirigir a verReceta.jsp
                    request.getRequestDispatcher("/verReceta.jsp").forward(request, response);
                } else {
                    // Si no se encuentra la receta, redirigir a foroReceta.jsp con un mensaje de error
                    request.setAttribute("error", "La receta no se ha encontrado.");
                    request.getRequestDispatcher("/foroReceta.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                // Si el ID no es válido, redirigir a foroReceta.jsp con un mensaje de error
                request.setAttribute("error", "ID de receta inválido.");
                request.getRequestDispatcher("/foroReceta.jsp").forward(request, response);
            }
        } else {
            // Si no se proporciona ID, redirigir a foroReceta.jsp con un mensaje de error
            request.setAttribute("error", "No se proporcionó un ID de receta.");
            request.getRequestDispatcher("/foroReceta.jsp").forward(request, response);
        }
    }
}
