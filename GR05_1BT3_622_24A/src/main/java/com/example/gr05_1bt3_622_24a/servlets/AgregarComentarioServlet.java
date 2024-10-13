package com.example.gr05_1bt3_622_24a.servlets;

import dao.ResenaJpaController;
import modelo.Comentario;
import modelo.Resena;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

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
            // Buscar la reseña asociada en la base de datos
            Resena resena = resenaJpaController.findResena(idResena);

            if (resena == null) {
                // Si la reseña no se encuentra, redirigir al foro con un error
                request.setAttribute("error", "No se pudo encontrar la reseña.");
                request.getRequestDispatcher("/foro.jsp").forward(request, response);
                return;
            }

            // Crear un nuevo comentario
            Comentario nuevoComentario = new Comentario();
            nuevoComentario.setContent(contenido);
            nuevoComentario.setDatePublish(LocalDateTime.now());
            nuevoComentario.setResena(resena);

            // Agregar el comentario a la lista de comentarios de la reseña
            resena.getListaComentarios().add(nuevoComentario);
            resenaJpaController.edit(resena);

            // Redirigir a verResena.jsp para mostrar la reseña y sus comentarios actualizados
            request.setAttribute("resena", resena);
            request.getRequestDispatcher("/verResena.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            // En caso de error, regresar a la página del comentario con un mensaje de error
            request.setAttribute("error", "Ocurrió un error al agregar el comentario.");
            request.getRequestDispatcher("/agregarComentario.jsp").forward(request, response);
        }
    }
}
