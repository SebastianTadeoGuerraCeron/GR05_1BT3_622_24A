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

    // El objeto foro ahora tiene la lista auxiliar donde se almacenarán las recetas recuperadas
    private final Foro foro = new Foro("Foro de Recetas");

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el filtro de tipo de receta desde el parámetro de la URL
        String filtroTipoReceta = request.getParameter("filtro-receta");

        // Recuperar todas las recetas de la base de datos mediante JPA y agregarlas a la lista auxiliar
        List<Receta> todasRecetas = recetaJpaController.findRecetaEntities();
        foro.setListaAuxRecetas(todasRecetas); // Almacenar en la lista auxiliar

        // Filtrar las recetas según el filtro seleccionado
        List<Receta> recetasFiltradas = Filtro.filtrarPorTipoReceta(foro.getListaAuxRecetas(), filtroTipoReceta);

        // Pasar la lista de recetas filtradas al JSP
        request.setAttribute("listaRecetas", recetasFiltradas);

        // Redirigir al foroRecetas.jsp para mostrar las recetas
        request.getRequestDispatcher("/foroReceta.jsp").forward(request, response);
    }
}
