<%@ page import="modelo.Receta" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Ampliar Receta</title>
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
  <h1>Ampliar Receta</h1>

  <%
    // Obtener la receta desde los atributos del request
    Receta recetaAmpliada = (Receta) request.getAttribute("receta");
    if (recetaAmpliada != null) {
  %>
  <h3><%= recetaAmpliada.getNombre() %> - <%= recetaAmpliada.getTipoReceta() %></h3>
  <p><em>Ingredientes: <%= recetaAmpliada.getIngredientes() %></em></p>
  <p>Instrucciones de preparación: <%= recetaAmpliada.getPreparacion() %></p>

  <!-- Botón para agregar un comentario -->
  <button class="boton-agregar" onclick="window.location.href='agregarComentario.jsp?id=<%= recetaAmpliada.getId() %>'">Agregar Comentario</button>

  <!-- Botón "Cancelar" -->
  <form action="ForoRecetaServlet" method="GET" style="display:inline;">
    <button type="submit" class="boton-cancelar">Cancelar</button>
  </form>

  <%
  } else {
  %>
  <p>La receta no se ha encontrado.</p>
  <button class="boton-cancelar" onclick="window.location.href='foroReceta.jsp'">Cancelar</button>
  <%
    }
  %>
</div>
</body>
</html>
