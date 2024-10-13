package com.example.gr05_1bt3_622_24a.servlets;

import dao.ResenaJpaController;
import modelo.Foro;
import modelo.Resena;
import negocio.Filtro;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ForoServlet")
public class ForoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ResenaJpaController resenaJpaController = new ResenaJpaController();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el filtro de comida
        String filtroCategoria = request.getParameter("filtro-comida");

        // Crear una instancia de Foro
        Foro foro = new Foro("Foro de Reseñas");

        // Utilizar el método de la clase Foro para obtener todas las reseñas
        List<Resena> listaResenas = foro.mostrarResenas(resenaJpaController);

        // Filtrar la lista de reseñas usando la clase Filtro, si el filtro es válido
        listaResenas = Filtro.filtrarPorCategoria(listaResenas, filtroCategoria);

        // Pasar la lista de reseñas filtradas a foro.jsp
        request.setAttribute("listaResenas", listaResenas);

        // Redirigir a foro.jsp
        request.getRequestDispatcher("/foro.jsp").forward(request, response);
    }
}
