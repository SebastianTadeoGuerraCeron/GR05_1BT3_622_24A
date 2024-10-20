package com.example.gr05_1bt3_622_24a.servlets;

import dao.RecetaJpaController;
import dao.ForoJpaController;
import modelo.Receta;
import modelo.Foro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AgregarRecetaServlet")
public class AgregarRecetaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Crear instancias de los controladores JPA
    private RecetaJpaController recetaJpaController = new RecetaJpaController();
    private ForoJpaController foroJpaController = new ForoJpaController(); // Controlador para obtener el foro

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
            // Aquí debes obtener el Foro de alguna manera
            // Por simplicidad, en este ejemplo se asume que hay un foro por defecto con ID 1
            Foro foro = foroJpaController.findForo(1L);  // Puedes cambiar el ID o la lógica para obtener el foro adecuado

            // Publicar la receta en el foro (asociar la receta con el foro)
            receta.publicarReceta(foro);  // Llamada al método que asocia la receta con el foro

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
