package com.example.gr05_1bt3_622_24a.servlets;

import dao.RecetaJpaController;
import modelo.Receta;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AgregarRecetaServletTest {

    @Test
    public void given_receta_when_doPost_then_nuevaReceta() throws ServletException, IOException {
        // Arrange
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        RecetaJpaController recetaJpaController = Mockito.mock(RecetaJpaController.class);
        AgregarRecetaServlet agregarRecetaServlet = new AgregarRecetaServlet(recetaJpaController);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        when(request.getParameter("nombre")).thenReturn("nombre");
        when(request.getParameter("ingredientes")).thenReturn("ingredientes");
        when(request.getParameter("preparacion")).thenReturn("preparacion");

        // Act
        agregarRecetaServlet.doPost(request, response);

        // Assert
        verify(recetaJpaController, times(1)).create(any(Receta.class));
        assertTrue(stringWriter.toString().contains("nuevaReceta"));
    }
}
