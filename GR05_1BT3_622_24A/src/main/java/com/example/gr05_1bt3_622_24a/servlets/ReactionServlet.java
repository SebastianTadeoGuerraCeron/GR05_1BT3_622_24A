package com.example.gr05_1bt3_622_24a.servlets;

import dao.ResenaJpaController;
import modelo.Resena;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ReactionServlet")
public class ReactionServlet extends HttpServlet {

    private ResenaJpaController resenaController = new ResenaJpaController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");  // 'like' o 'dislike'
        Long resenaId = Long.parseLong(request.getParameter("resenaId"));

        try {
            // Buscar la reseña
            Resena resena = resenaController.findResena(resenaId);
            if (UpdateReaccion(response, action, resena)) return;
            CargarResena(request, response);

        } catch (Exception e) {
            ErrroresReact(response, e);
        }
    }

    private static void ErrroresReact(HttpServletResponse response, Exception e) throws IOException {
        e.printStackTrace();
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la reacción");
    }

    private void CargarResena(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Resena> listaResenas = resenaController.findResenaEntities();
        request.setAttribute("listaResenas", listaResenas);
        request.getRequestDispatcher("/foro.jsp").forward(request, response);
    }

    private boolean UpdateReaccion(HttpServletResponse response, String action, Resena resena) throws Exception {
        if ("like".equals(action)) {
            resena.aumentarLikes();
        } else if ("dislike".equals(action)) {
            resena.aumentarDislikes();
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no válida");
            return true;
        }

        resenaController.edit(resena);
        return false;
    }
}