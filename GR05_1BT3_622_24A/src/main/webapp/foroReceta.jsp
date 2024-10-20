<%@ page import="modelo.Receta" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Las Huequitas - Foro de Recetas</title>
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
        .combo-box, .boton-resena {
            display: flex;
            align-items: center;
        }
        .combo-box {
            width: 70%;
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
        .boton-receta button {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 10px;
            font-size: 16px;
            margin-left: 10px;
        }
        .boton-receta button:hover {
            background-color: #45a049;
        }
        .boton-resena button {
            background-color: #008CBA;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 10px;
            font-size: 16px;
            margin-left: 10px;
        }
        .boton-resena button:hover {
            background-color: #007BB5;
        }
        #recetas {
            border-top: 2px solid #333;
            padding-top: 20px;
        }
        .receta {
            border-bottom: 1px solid #ccc;
            padding: 10px 0;
        }
        .receta h3 {
            margin: 0;
            font-size: 18px;
        }
        .receta p {
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
            <!-- El filtro de recetas que envía el formulario cuando se selecciona una opción -->
            <form method="GET" action="RecetaServlet">
                <label for="filtro-receta">Filtrar por Tipo de Receta:</label>
                <select id="filtro-receta" name="filtro-receta" onchange="this.form.submit()">
                    <option value="ALL" <%= request.getParameter("filtro-receta") == null || request.getParameter("filtro-receta").equals("ALL") ? "selected" : "" %>>Todo</option>
                    <option value="Postre" <%= "Postre".equals(request.getParameter("filtro-receta")) ? "selected" : "" %>>Postre</option>
                    <option value="Entrada" <%= "Entrada".equals(request.getParameter("filtro-receta")) ? "selected" : "" %>>Entrada</option>
                    <option value="Plato fuerte" <%= "Plato fuerte".equals(request.getParameter("filtro-receta")) ? "selected" : "" %>>Plato Fuerte</option>
                </select>
            </form>

            <!-- Botón Nueva Receta al lado del combo de selección -->
            <div class="boton-receta">
                <button onclick="window.location.href='nuevaReceta.jsp'">Nueva Receta</button>
            </div>
        </div>

        <!-- Botón para ir a Reseñas que redirige a foro.jsp -->
        <div class="boton-resena">
            <button onclick="window.location.href='foro.jsp'">Reseñas</button>
        </div>
    </div>

    <div id="recetas">
        <h2>Recetas</h2>
        <%
            // Obtener la lista de recetas filtradas del Servlet
            List<Receta> listaRecetas = (List<Receta>) request.getAttribute("listaRecetas");
            if (listaRecetas != null && !listaRecetas.isEmpty()) {
                // Iterar sobre las recetas y mostrarlas
                for (Receta receta : listaRecetas) {
        %>
        <div class="receta">
            <h3><%= receta.getNombre() %></h3>
            <h3>Tipo de Receta: <%= receta.getTipoReceta() %></h3>
            <p><em><%= receta.getIngredientes() %></em></p>
            <p><%= receta.getPreparacion() %></p>

            <!-- Botón para ampliar la receta -->
            <button class="boton-ampliar" onclick="window.location.href='AmpliarRecetaServlet?id=<%= receta.getId() %>'">Ampliar</button>
        </div>
        <%
            }
        } else {
        %>
        <p>No hay recetas aún.</p>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
