package com.example.gr05_1bt3_622_24a.servlets;

import dao.RecetaJpaController;
import dao.ForoJpaController;
import modelo.Receta;
import modelo.Foro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.io.IOException;

import static org.mockito.Mockito.*;

class AgregarRecetaServletTest {

    @Mock
    private RecetaJpaController recetaJpaController;

    @Mock
    private ForoJpaController foroJpaController;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @InjectMocks
    private AgregarRecetaServlet agregarRecetaServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void given_invalid_receta_when_post_then_show_error() throws Exception {
        // Configurar una receta con parámetros vacíos
        when(request.getParameter("nombre")).thenReturn("");
        when(request.getParameter("tipoReceta")).thenReturn("");
        when(request.getParameter("ingredientes")).thenReturn("");
        when(request.getParameter("preparacion")).thenReturn("");

        // Simular la obtención del RequestDispatcher para la redirección
        when(request.getRequestDispatcher("/agregarReceta.jsp")).thenReturn(requestDispatcher);

        // Llamar al método POST del servlet con datos inválidos
        agregarRecetaServlet.doPost(request, response);

        // Verificar que no se llama a la creación de la receta
        verify(recetaJpaController, times(0)).create(any(Receta.class));

        // Verificar que se redirige a la página de error
        verify(requestDispatcher).forward(request, response);
    }
}