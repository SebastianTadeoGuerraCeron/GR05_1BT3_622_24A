package com.example.gr05_1bt3_622_24a.servlets;

import dao.ResenaJpaController;
import modelo.Resena;
import modelo.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet("/NuevaResenaServlet")
public class NuevaResenaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(NuevaResenaServlet.class.getName());

    private final ResenaJpaController resenaJpaController = new ResenaJpaController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoria = request.getParameter("categoria");
        String restaurante = request.getParameter("restaurante");
        String contenido = request.getParameter("contenido");

        try {
            // Obtener el usuario autenticado desde la sesión
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioActual");
            if (usuario == null) {
                // Simulación temporal de un usuario si no está en la sesión
                usuario = new Usuario("userID");
                logger.warning("Usuario no autenticado. Simulando usuario.");
            }

            // El usuario crea la reseña
            Resena nuevaResena = usuario.createResena(categoria, restaurante, contenido, resenaJpaController);

            // Obtener la lista actualizada de reseñas
            List<Resena> listaResenas = resenaJpaController.findResenaEntities();
            request.setAttribute("listaResenas", listaResenas);

            // Redirigir a foro.jsp
            request.getRequestDispatcher("/foro.jsp").forward(request, response);

            logger.info("Reseña agregada con éxito con ID: " + nuevaResena.getResenaID());

        } catch (Exception e) {
            logger.severe("Error al agregar reseña: " + e.getMessage());
            e.printStackTrace(); // Imprimir el error en los logs
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/nuevaResena.jsp").forward(request, response);
        }
    }
}
