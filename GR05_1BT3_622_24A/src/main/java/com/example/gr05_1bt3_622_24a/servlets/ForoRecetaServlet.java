package com.example.gr05_1bt3_622_24a.servlets;

import dao.ForoRecetaJpaController;
import modelo.ForoReceta;
import modelo.Receta;
import negocio.Filtro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ForoRecetaServlet")
public class ForoRecetaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ForoRecetaJpaController foroRecetaJpaController = new ForoRecetaJpaController();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el filtro de tipo de receta desde los parámetros de la solicitud
        String filtroTipoReceta = request.getParameter("filtro-tipo");

        // Obtener el foro de recetas desde la base de datos
        ForoReceta foroReceta = foroRecetaJpaController.findForoReceta(1L); // Asume que hay un foro con ID 1

        // Filtrar la lista de recetas según el tipo de receta
        List<Receta> listaRecetas = Filtro.obtenerYFiltrarRecetas(filtroTipoReceta, foroReceta);

        // Pasar la lista de recetas filtradas al JSP
        request.setAttribute("listaRecetas", listaRecetas);

        // Redirigir al foroReceta.jsp para mostrar las recetas
        request.getRequestDispatcher("/foroReceta.jsp").forward(request, response);
    }
}
