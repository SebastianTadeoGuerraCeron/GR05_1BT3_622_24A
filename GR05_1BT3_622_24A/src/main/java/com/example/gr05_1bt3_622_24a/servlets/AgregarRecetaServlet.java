package com.example.gr05_1bt3_622_24a.servlets;

import dao.RecetaJpaController;
import modelo.Receta;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AgregarRecetaServlet")
public class AgregarRecetaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Crear una instancia del controlador JPA
    private RecetaJpaController recetaJpaController = new RecetaJpaController();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String nombre = request.getParameter("nombre");
        String tipoReceta = request.getParameter("tipoReceta");
        String ingredientes = request.getParameter("ingredientes");
        String preparacion = request.getParameter("preparacion");

        // Validar que los campos no sean nulos o vacíos
        if (nombre == null || nombre.isEmpty() || tipoReceta == null || tipoReceta.isEmpty() ||
                ingredientes == null || ingredientes.isEmpty() || preparacion == null || preparacion.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("/agregarReceta.jsp").forward(request, response);
            return;
        }

        // Crear una nueva instancia de Receta con los datos del formulario
        Receta receta = new Receta(nombre, tipoReceta, ingredientes, preparacion);

        try {
            // Guardar la receta en la base de datos
            recetaJpaController.create(receta);
            // Redirigir a una página de éxito o al listado de recetas
            response.sendRedirect("foroReceta.jsp");
        } catch (Exception e) {
            e.printStackTrace();  // Capturar y mostrar cualquier error
            request.setAttribute("error", "Error al guardar la receta.");
            request.getRequestDispatcher("/agregarReceta.jsp").forward(request, response);
        }
    }
}
