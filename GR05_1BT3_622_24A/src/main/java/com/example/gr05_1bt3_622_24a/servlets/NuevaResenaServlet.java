package com.example.gr05_1bt3_622_24a.servlets;

import dao.ResenaJpaController;
import modelo.Resena;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@WebServlet("/NuevaResenaServlet")
public class NuevaResenaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(NuevaResenaServlet.class.getName());

    // Crear instancia del controlador JPA
    private final ResenaJpaController resenaJpaController = new ResenaJpaController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario
        String categoria = request.getParameter("categoria");
        String restaurante = request.getParameter("restaurante");
        String contenido = request.getParameter("contenido");

        // Verificar que los campos obligatorios no estén vacíos
        if (categoria == null || categoria.isEmpty() ||
                restaurante == null || restaurante.isEmpty() ||
                contenido == null || contenido.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("/nuevaResena.jsp").forward(request, response);
            return;
        }

        // Verificar contenido ofensivo
        if (verificarContenidoOfensivo(contenido)) {
            request.setAttribute("error", "La reseña contiene palabras ofensivas.");
            request.getRequestDispatcher("/nuevaResena.jsp").forward(request, response);
            return;
        }

        try {
            // Crear una nueva instancia de Resena con un resenaID único
            Resena nuevaResena = new Resena();
            nuevaResena.setResenaID(UUID.randomUUID().toString()); // Generar un resenaID aleatorio
            nuevaResena.setCategoria(categoria);
            nuevaResena.setRestaurant(restaurante);
            nuevaResena.setContenido(contenido);
            nuevaResena.setFechaPublicacion(LocalDateTime.now());

            // Persistir la reseña en la base de datos usando JPA
            resenaJpaController.create(nuevaResena);

            // Obtener la lista actualizada de reseñas desde la base de datos
            List<Resena> listaResenas = resenaJpaController.findResenaEntities();

            // Pasar la lista de reseñas al JSP
            request.setAttribute("listaResenas", listaResenas);


            // Redirigir a foro.jsp
            request.getRequestDispatcher("/foro.jsp").forward(request, response);

            // Log para depuración
            logger.info("Reseña agregada y persistida con resenaID: " + nuevaResena.getResenaID() + ". Número total de reseñas: " + resenaJpaController.getResenaCount());

        } catch (Exception e) {
            logger.severe("Error al agregar reseña: " + e.getMessage());
            request.setAttribute("error", "Ocurrió un error al agregar la reseña.");
            request.getRequestDispatcher("/nuevaResena.jsp").forward(request, response);
        }
    }

    // Método para verificar contenido ofensivo
    private boolean verificarContenidoOfensivo(String contenido) {
        String[] palabrasOfensivas = {"malaPalabra1", "malaPalabra2"};
        for (String palabra : palabrasOfensivas) {
            if (contenido.toLowerCase().contains(palabra.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
