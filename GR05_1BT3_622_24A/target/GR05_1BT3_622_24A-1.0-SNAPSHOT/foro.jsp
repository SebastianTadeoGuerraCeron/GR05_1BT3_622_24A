<%@ page import="com.example.gr05_1bt3_622_24a.Resena" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Las Huequitas - Foro de Reseñas</title>
</head>
<body>
<h1>Las Huequitas</h1>

<!-- Botón para agregar nueva reseña -->
<div>
    <button onclick="window.location.href='nuevaResena.jsp'">Nueva Reseña</button>
</div>

<!-- Aquí se listarán las reseñas del foro -->
<div id="resenas">
    <h2>Reseñas</h2>
    <%
        // Aquí puedes obtener la lista de reseñas desde un Servlet o Base de Datos
        List<Resena> listaResenas = (List<Resena>) request.getAttribute("listaResenas");
        if (listaResenas != null && !listaResenas.isEmpty()) {
            for (Resena resena : listaResenas) {
    %>
    <div>
        <h3><%= resena.getRestaurant() %> - <%= resena.getCategoria() %></h3>
        <p><%= resena.getFechaPublicacion() %></p>
        <p><%= resena.getContenido() %></p>
    </div>
    <%
            }
        } else {
    %>
    <p>No hay reseñas aún.</p>
    <%
        }
    %>
</div>
</body>
</html>