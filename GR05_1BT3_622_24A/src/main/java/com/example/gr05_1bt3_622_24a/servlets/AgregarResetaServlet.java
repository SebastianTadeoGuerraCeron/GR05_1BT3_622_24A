package com.example.gr05_1bt3_622_24a.servlets;

import dao.ResenaJpaController;
import dao.ResetaJpaController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AgregarResetaServlet")
public class AgregarResetaServlet {
    private static final long serialVersionUID = 1L;
    private ResetaJpaController resetaJpaController = new ResetaJpaController();

    public AgregarResetaServlet(ResetaJpaController resetaJpaController) {
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }



}
