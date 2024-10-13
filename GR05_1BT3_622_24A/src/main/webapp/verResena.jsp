<%@ page import="java.util.List" %>
<%@ page import="modelo.Resena" %>
<%@ page import="modelo.Comentario" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ampliar Resena</title>
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
        .comentarios {
            margin-top: 30px;
        }
        .comentario {
            border-top: 1px solid #ccc;
            padding-top: 10px;
            margin-top: 10px;
        }
        .comentario p {
            margin: 0;
        }

        /* Estilo de los botones */
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
        // Obtener la reseña desde los atributos del request
        Resena resenaAmpliada = (Resena) request.getAttribute("resena");
        if (resenaAmpliada != null) {
    %>
    <h3><%= resenaAmpliada.getRestaurant() %> - <%= resenaAmpliada.getCategoria() %></h3>
    <p><em><%= resenaAmpliada.getFechaPublicacion() %></em></p>
    <p><%= resenaAmpliada.getContenido() %></p>

    <!-- Botón para agregar un comentario -->
    <button class="boton-agregar" onclick="window.location.href='agregarComentario.jsp?id=<%= resenaAmpliada.getId() %>'">Agregar Comentario</button>

    <!-- Botón "Cancelar" -->
    <form action="ForoServlet" method="GET" style="display:inline;">
        <button type="submit" class="boton-cancelar">Cancelar</button>
    </form>

    <!-- Mostrar los comentarios asociados a la reseña -->
    <div class="comentarios">
        <h2>Comentarios</h2>
        <%
            // Verificar si hay comentarios asociados a la reseña
            List<Comentario> listaComentarios = resenaAmpliada.getListaComentarios();
            if (listaComentarios != null && !listaComentarios.isEmpty()) {
                for (Comentario comentario : listaComentarios) {
        %>
        <div class="comentario">
            <p><strong>Publicado:</strong> <%= comentario.getDatePublish() %></p>
            <p><%= comentario.getContent() %></p>
        </div>
        <%
            }
        } else {
        %>
        <p>No hay comentarios para esta reseña.</p>
        <%
            }
        %>
    </div>

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
