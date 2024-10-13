<%@ page import="modelo.Resena" %>
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
            font-size: 60px;
        }
        .filtro-boton {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .combo-box {
            width: 200px;
        }
        label {
            font-weight: bold;
            margin-bottom: 10px;
        }
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }
        .boton-resena button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 10px;
            font-size: 16px;
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
        .boton-ampliar {
            background-color: #008CBA;
            color: white;
            border: none;
            padding: 5px 10px;
            cursor: pointer;
            border-radius: 5px;
            font-size: 14px;
            margin-top: 10px;
        }
        .boton-ampliar:hover {
            background-color: #007BB5;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Las Huequitas</h1>

    <div class="filtro-boton">
        <div class="combo-box">
            <!-- El filtro de comida que envía el formulario cuando se selecciona una opción -->
            <form method="GET" action="ForoServlet">
                <label for="filtro-comida">Filtrar por Comida:</label>
                <select id="filtro-comida" name="filtro-comida" onchange="this.form.submit()">
                    <option value="ALL" <%= request.getParameter("filtro-comida") == null || request.getParameter("filtro-comida").equals("ALL") ? "selected" : "" %>>Todo</option>
                    <option value="chatarra" <%= "chatarra".equals(request.getParameter("filtro-comida")) ? "selected" : "" %>>Chatarra</option>
                    <option value="tradicional" <%= "tradicional".equals(request.getParameter("filtro-comida")) ? "selected" : "" %>>Tradicional</option>
                </select>
            </form>
        </div>

        <div class="boton-resena">
            <button onclick="window.location.href='nuevaResena.jsp'">Nueva Reseña</button>
        </div>
    </div>

    <div id="resenas">
        <h2>Reseñas</h2>
        <%
            // Obtener la lista de reseñas filtradas del Servlet
            List<Resena> listaResenas = (List<Resena>) request.getAttribute("listaResenas");
            if (listaResenas != null && !listaResenas.isEmpty()) {
                // Iterar sobre las reseñas y mostrarlas
                for (Resena resena : listaResenas) {
        %>
        <div class="resena">
            <h3><%= resena.getRestaurant() %></h3>
            <h3>Comida: <%= resena.getCategoria() %></h3>
            <p><em><%= resena.getFechaPublicacion() %></em></p>
            <p><%= resena.getContenido() %></p>
            <!-- Botón para ampliar la reseña -->
            <button class="boton-ampliar" onclick="window.location.href='AmpliarResenaServlet?id=<%= resena.getId() %>'">Ampliar</button>
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
