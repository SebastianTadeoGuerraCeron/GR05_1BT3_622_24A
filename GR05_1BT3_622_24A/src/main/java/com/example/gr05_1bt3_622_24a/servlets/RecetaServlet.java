package com.example.gr05_1bt3_622_24a.servlets;

import dao.RecetaJpaController;
import modelo.Foro;
import modelo.Receta;
import negocio.Filtro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RecetaServlet")
public class RecetaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final RecetaJpaController recetaJpaController = new RecetaJpaController();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el filtro de tipo de receta desde el parámetro de la URL
        String filtroTipoReceta = request.getParameter("filtro-receta");

        // Crear una instancia del foro de recetas (puedes ajustar si ya tienes una instancia en uso)
        Foro foro = new Foro("Foro de Recetas");

        // Obtener la lista completa de recetas
        List<Receta> listaRecetas = foro.getListaReceta();

        // Filtrar la lista de recetas según el tipo seleccionado
        List<Receta> recetasFiltradas = Filtro.filtrarPorTipoReceta(listaRecetas, filtroTipoReceta);

        // Pasar la lista de recetas filtradas al JSP
        request.setAttribute("listaRecetas", recetasFiltradas);

        // Redirigir a foroRecetas.jsp para mostrar las recetas
        request.getRequestDispatcher("/foroRecetas.jsp").forward(request, response);
    }
}
