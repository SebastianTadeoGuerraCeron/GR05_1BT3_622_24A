package com.example.gr05_1bt3_622_24a.servlets;

import dao.RecetaJpaController;
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
    private RecetaJpaController recetaJpaController;

    public AgregarRecetaServlet(RecetaJpaController recetaJpaController) {
        this.recetaJpaController = recetaJpaController;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros de la solicitud
        String nombre = request.getParameter("nombre");
        String tipoReceta = request.getParameter("tipoReceta");
        String ingredientes = request.getParameter("ingredientes");
        String preparacion = request.getParameter("preparacion");

        // Crear una nueva instancia de Receta
        Receta receta = new Receta(nombre, tipoReceta, ingredientes, preparacion);

        // Simular el foro donde se publicará la receta (esto debería obtenerse desde la base de datos o el contexto de la aplicación)
        Foro foro = new Foro();  // Asegúrate de tener una instancia válida de Foro

        // Publicar la receta en el foro
        receta.publicarReceta(foro);

        try {
            // Guardar la receta en la base de datos
            recetaJpaController.create(receta);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Enviar respuesta de éxito al cliente
        response.getWriter().write("nuevaReceta");
    }
}
