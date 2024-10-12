package com.example.gr05_1bt3_622_24a.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Resena;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.ArrayList;

@WebServlet("/NuevaResenaServlet")
public class NuevaResenaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(NuevaResenaServlet.class.getName());
    private static final ArrayList<Resena> listaResenas = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoria = request.getParameter("categoria");
        String restaurante = request.getParameter("restaurante");
        String contenido = request.getParameter("contenido");

        if (categoria == null || categoria.isEmpty() ||
                restaurante == null || restaurante.isEmpty() ||
                contenido == null || contenido.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("/nuevaResena.jsp").forward(request, response);
            return;
        }

        if (verificarContenidoOfensivo(contenido)) {
            request.setAttribute("error", "La reseña contiene palabras ofensivas.");
            request.getRequestDispatcher("/nuevaResena.jsp").forward(request, response);
            return;
        }

        Resena nuevaResena = new Resena();
        nuevaResena.setCategoria(categoria);
        nuevaResena.setRestaurant(restaurante);
        nuevaResena.setContenido(contenido);
        nuevaResena.setFechaPublicacion(java.time.LocalDateTime.now());

        listaResenas.add(nuevaResena);

        request.setAttribute("listaResenas", listaResenas);
        request.getRequestDispatcher("/foro.jsp").forward(request, response);

        logger.info("Setting listaResenas attribute with " + listaResenas.size() + " items.");
        request.setAttribute("listaResenas", listaResenas);
        request.getRequestDispatcher("/foro.jsp").forward(request, response);
    }

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