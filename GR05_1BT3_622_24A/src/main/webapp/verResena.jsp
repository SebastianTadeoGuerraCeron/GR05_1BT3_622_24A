<%@ page import="modelo.Resena" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ampliar Reseña</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            width: 60%;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
        }
        h1 {
            text-align: center;
            font-size: 40px;
        }
        .boton-agregar, .boton-cancelar {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            margin-right: 10px;
        }
        .boton-agregar {
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 10px;
        }
        .boton-agregar:hover {
            background-color: #45a049;
        }
        .boton-cancelar {
            background-color: #f44336;
            color: white;
            border: none;
            border-radius: 10px;
        }
        .boton-cancelar:hover {
            background-color: #e53935;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Ampliar Reseña</h1>

    <%
        Resena resenaAmpliada = (Resena) request.getAttribute("resena");
        if (resenaAmpliada != null) {
    %>
    <h3><%= resenaAmpliada.getRestaurant() %> - <%= resenaAmpliada.getCategoria() %></h3>
    <p><em><%= resenaAmpliada.getFechaPublicacion() %></em></p>
    <p><%= resenaAmpliada.getContenido() %></p>

    <!-- Botones -->
    <button class="boton-agregar" onclick="window.location.href='agregarComentario.jsp?id=<%= resenaAmpliada.getId() %>'">Agregar Comentario</button>
    <button class="boton-cancelar" onclick="window.location.href='foro.jsp'">Cancelar</button>

    <%
    } else {
    %>
    <p>La reseña no se ha encontrado.</p>
    <button class="boton-cancelar" onclick="window.location.href='foro.jsp'">Cancelar</button>
    <%
        }
    %>
</div>
</body>
</html>
