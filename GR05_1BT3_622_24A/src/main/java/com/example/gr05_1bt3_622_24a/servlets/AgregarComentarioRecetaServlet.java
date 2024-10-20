package com.example.gr05_1bt3_622_24a.servlets;

import dao.RecetaJpaController;
import dao.ComentarioRecetaJpaController;
import modelo.ComentarioReceta;
import modelo.Receta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/AgregarComentarioRecetaServlet")
public class AgregarComentarioRecetaServlet extends HttpServlet {

    private RecetaJpaController recetaJpaController = new RecetaJpaController();
    private ComentarioRecetaJpaController comentarioRecetaJpaController = new ComentarioRecetaJpaController();

    // Setters para inyectar los controladores en las pruebas
    public void setRecetaJpaController(RecetaJpaController recetaJpaController) {
        this.recetaJpaController = recetaJpaController;
    }

    public void setComentarioRecetaJpaController(ComentarioRecetaJpaController comentarioRecetaJpaController) {
        this.comentarioRecetaJpaController = comentarioRecetaJpaController;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contenido = request.getParameter("contenido");
        String idRecetaParam = request.getParameter("idReceta");

        try {
            // Validar el contenido del comentario
            if (contenido == null || contenido.trim().isEmpty()) {
                throw new Exception("Debe proporcionar un contenido válido para el comentario.");
            }

            Long idReceta = Long.parseLong(idRecetaParam);

            // Buscar la receta
            Receta receta = recetaJpaController.findReceta(idReceta);
            if (receta == null) {
                throw new Exception("Receta no encontrada.");
            }

            // Crear y agregar el comentario a la receta
            ComentarioReceta comentario = new ComentarioReceta(contenido, LocalDateTime.now());
            if (comentario.verificarContenidoOfensivo()) {
                // Si se detecta contenido ofensivo, se guarda el mensaje en el request
                request.setAttribute("ofensivo", true);
                request.getRequestDispatcher("/agregarComentario.jsp").forward(request, response);
                return; // Terminar aquí, no guardar la receta
            }

            comentario.setReceta(receta);
            receta.agregarComentario(comentario);
            comentarioRecetaJpaController.create(comentario);

            // Redirigir a la página verReceta.jsp
            request.setAttribute("receta", receta);
            request.getRequestDispatcher("/verReceta.jsp").forward(request, response);

        } catch (Exception e) {
            // Manejar errores y redirigir a la página de agregar comentario con el mensaje de error
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/agregarComentario.jsp").forward(request, response);
        }
    }
}
