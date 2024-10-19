package com.example.gr05_1bt3_622_24a.servlets;

import com.example.gr05_1bt3_622_24a.servlets.AgregarComentarioRecetaServlet;
import modelo.Receta;
import modelo.ComentarioReceta;
import dao.RecetaJpaController;
import dao.ComentarioRecetaJpaController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import java.io.IOException;

import static org.mockito.Mockito.*;

public class AgregarComentarioRecetaServletTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    private RecetaJpaController recetaJpaController;
    private ComentarioRecetaJpaController comentarioRecetaJpaController;
    private AgregarComentarioRecetaServlet servlet;

    @BeforeEach
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        requestDispatcher = mock(RequestDispatcher.class);
        recetaJpaController = mock(RecetaJpaController.class);
        comentarioRecetaJpaController = mock(ComentarioRecetaJpaController.class);

        servlet = new AgregarComentarioRecetaServlet();
        servlet.setRecetaJpaController(recetaJpaController);
        servlet.setComentarioRecetaJpaController(comentarioRecetaJpaController);
    }

    @Test
    public void given_receta_when_AgregarComentarioReceta_then_nuevoComentarioReceta() throws ServletException, IOException {
        // Simular los parámetros enviados en la solicitud
        when(request.getParameter("contenido")).thenReturn("Delicious recipe!");
        when(request.getParameter("idReceta")).thenReturn("1");

        // Simular la receta que se buscará
        Receta receta = new Receta("Tarta de manzana", "Postre", "Manzana, azúcar", "Mezclar y hornear");
        when(recetaJpaController.findReceta(1L)).thenReturn(receta);

        // Simular el redireccionamiento
        when(request.getRequestDispatcher("/verReceta.jsp")).thenReturn(requestDispatcher);

        // Ejecutar el método doPost del servlet
        servlet.doPost(request, response);

        // Verificar que el comentario fue agregado a la receta
        verify(comentarioRecetaJpaController).create(any(ComentarioReceta.class));

        // Verificar que el servlet redirigió correctamente a la página verReceta.jsp
        verify(request).getRequestDispatcher("/verReceta.jsp");
        verify(requestDispatcher).forward(request, response);
    }

    @Test
    public void given_receta_when_AgregarComentarioReceta_then_ComentarioVacio() throws ServletException, IOException {
        // Simular un contenido de comentario inválido (vacío)
        when(request.getParameter("contenido")).thenReturn("");

        // Simular el redireccionamiento a la página de error
        when(request.getRequestDispatcher("/agregarComentario.jsp")).thenReturn(requestDispatcher);

        // Ejecutar el método doPost del servlet
        servlet.doPost(request, response);

        // Verificar que no se llamó a la creación del comentario
        verify(comentarioRecetaJpaController, never()).create(any(ComentarioReceta.class));

        // Verificar que el servlet redirigió correctamente a la página agregarComentario.jsp con un error
        verify(request).setAttribute(eq("error"), anyString());
        verify(request).getRequestDispatcher("/agregarComentario.jsp");
        verify(requestDispatcher).forward(request, response);
    }
}
