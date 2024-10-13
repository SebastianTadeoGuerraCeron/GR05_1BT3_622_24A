package com.example.gr05_1bt3_622_24a.servlets;

import dao.ComentarioJpaController;
import dao.ResenaJpaController;
import modelo.Comentario;
import modelo.Usuario;
import negocio.ModeradorComplete;
import negocio.ModeradorOfensivo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/AgregarComentarioServlet")
public class AgregarComentarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(AgregarComentarioServlet.class.getName());

    private ResenaJpaController resenaJpaController = new ResenaJpaController();
    private ComentarioJpaController comentarioJpaController = new ComentarioJpaController();

    // Instanciar el moderador para verificar contenido ofensivo
    private ModeradorOfensivo moderadorOfensivo = new ModeradorOfensivo();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el contenido del comentario y el ID de la reseña
        String contenido = request.getParameter("contenido");
        String idResenaParam = request.getParameter("idResena");

        try {
            // Verificar si el contenido del comentario contiene palabras ofensivas
            if (moderadorOfensivo.verificarOfensivo(contenido)) {
                // Si contiene palabras ofensivas, redirigir con un mensaje de error
                throw new Exception("El comentario contiene palabras ofensivas. Por favor, revisa tu contenido.");
            }

            // Verificar si el ID de la reseña o el contenido del comentario son inválidos
            if (idResenaParam == null || !ModeradorComplete.esComentarioValido(contenido)) {
                throw new Exception("Debe proporcionar un contenido válido para el comentario.");
            }

            Long idResena = Long.parseLong(idResenaParam);

            // Crear el usuario (en este caso, se pasa un userID quemado, pero deberías obtenerlo del contexto de sesión)
            Usuario usuario = new Usuario("userID");  // Aquí deberías obtener el ID del usuario desde la sesión

            // Usar el método de la clase Usuario para agregar el comentario
            usuario.createComment(contenido, idResena, comentarioJpaController, resenaJpaController);

            // Redirigir a verResena.jsp para mostrar la reseña y sus comentarios actualizados
            request.setAttribute("resena", resenaJpaController.findResena(idResena));
            request.getRequestDispatcher("/verResena.jsp").forward(request, response);

            logger.info("Comentario agregado con éxito a la reseña con ID: " + idResena);

        } catch (Exception e) {
            // En caso de error (como una palabra ofensiva), regresar a la página con un mensaje de error
            logger.severe("Error al agregar comentario: " + e.getMessage());
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/agregarComentario.jsp").forward(request, response);
        }
    }
}
