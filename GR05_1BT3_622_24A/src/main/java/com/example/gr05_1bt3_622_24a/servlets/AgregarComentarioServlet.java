package com.example.gr05_1bt3_622_24a.servlets;

import dao.ResenaJpaController;
import modelo.Comentario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AgregarComentarioServlet")
public class AgregarComentarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ResenaJpaController resenaJpaController = new ResenaJpaController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el contenido del comentario y el ID de la reseña
        String contenido = request.getParameter("contenido");
        String idResenaParam = request.getParameter("idResena");

        if (idResenaParam == null || contenido == null || contenido.isEmpty()) {
            // Si no se proporciona un ID o el contenido está vacío, redirigir con un mensaje de error
            request.setAttribute("error", "Debe proporcionar un contenido válido para el comentario.");
            request.getRequestDispatcher("/agregarComentario.jsp").forward(request, response);
            return;
        }

        try {
            Long idResena = Long.parseLong(idResenaParam);

            // Usar el método estático de la clase Comentario para publicar el comentario
            Comentario.publicarComentario(contenido, idResena, resenaJpaController);

            // Redirigir a verResena.jsp para mostrar la reseña y sus comentarios actualizados
            request.setAttribute("resena", resenaJpaController.findResena(idResena));
            request.getRequestDispatcher("/verResena.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // En caso de error, regresar a la página del comentario con un mensaje de error
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/agregarComentario.jsp").forward(request, response);
        }
    }
}
