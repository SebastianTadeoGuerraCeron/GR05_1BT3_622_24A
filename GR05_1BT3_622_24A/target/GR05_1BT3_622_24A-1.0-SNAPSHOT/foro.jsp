<%@ page import="com.example.gr05_1bt3_622_24a.Resena" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Las Huequitas - Foro de Reseñas</title>
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
        }
        .boton-resena {
            display: flex;
            justify-content: flex-end;
            margin-bottom: 20px;
        }
        .boton-resena button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }
        .boton-resena button:hover {
            background-color: #45a049;
        }
        #resenas {
            border-top: 2px solid #333;
            padding-top: 20px;
        }
        .resena {
            border-bottom: 1px solid #ccc;
            padding: 10px 0;
        }
        .resena h3 {
            margin: 0;
            font-size: 18px;
        }
        .resena p {
            margin: 5px 0;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Las Huequitas</h1>

    <!-- Botón para agregar nueva reseña -->
    <div class="boton-resena">
        <button onclick="window.location.href='nuevaResena.jsp'">Nueva Reseña</button>
    </div>

    <!-- Aquí se listarán las reseñas del foro -->
    <div id="resenas">
        <h2>Reseñas</h2>
        <%
            // Obtener la lista de reseñas desde el Servlet o Base de Datos
            List<Resena> listaResenas = (List<Resena>) request.getAttribute("listaResenas");
            if (listaResenas != null && !listaResenas.isEmpty()) {
                for (Resena resena : listaResenas) {
        %>
        <div class="resena">
            <h3><%= resena.getRestaurant() %> - <%= resena.getCategoria() %></h3>
            <p><em><%= resena.getFechaPublicacion() %></em></p>
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
</div>
</body>
</html>
