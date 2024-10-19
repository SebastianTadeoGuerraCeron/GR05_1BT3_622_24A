package com.example.gr05_1bt3_622_24a.servlets;


import dao.ResetaJpaController;
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

class AgregarResetaServletTest {

    @Test
    public void given_reseta_when_doPost_then_nuevaReseta() throws ServletException, IOException {
        // Arrange
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        HttpServletResponse response = Mockito.mock(HttpServletResponse.class);
        ResetaJpaController resetaJpaController = Mockito.mock(ResetaJpaController.class);
        AgregarRecetaServlet agregarResetaServlet = new AgregarRecetaServlet(resetaJpaController);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(printWriter);
        when(request.getParameter("nombre")).thenReturn("nombre");
        when(request.getParameter("ingredientes")).thenReturn("ingredientes");
        when(request.getParameter("preparacion")).thenReturn("preparacion");

        // Act
        agregarResetaServlet.doPost(request, response);

        // Assert
        verify(resetaJpaController, times(1)).create(any(Receta.class));
        assertTrue(stringWriter.toString().contains("nuevaReseta"));
    }
}
