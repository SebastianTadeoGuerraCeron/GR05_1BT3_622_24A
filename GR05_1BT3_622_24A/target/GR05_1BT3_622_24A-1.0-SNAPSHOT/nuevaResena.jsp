<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Nueva Reseña - Las Huequitas</title>
  <style>
    /* Estilos generales */
    body {
      font-family: Arial, sans-serif;
    }
    .container {
      width: 60%;
      margin: 0 auto;
      padding: 40px;
      border: 1px solid #ccc;
      border-radius: 10px;
    }
    h1 {
      text-align: center;
    }
    /* Estilo del formulario */
    form {
      display: flex;
      flex-direction: column;
      gap: 15px;
    }
    label {
      font-weight: bold;
    }
    input[type="text"],
    select {
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
      width: 50%; /* Ocupa todo el ancho del contenedor */
      box-sizing: border-box; /* Asegura que padding y border no influyan en el tamaño */
    }
    textarea {
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
      width: 100%;
      height: 150px; /* Altura fija */
      resize: none;  /* No se puede redimensionar */
      box-sizing: border-box;
    }
    /* Estilos de los botones */
    .buttons {
      display: flex;
      justify-content: space-between;
      margin-top: 20px;
    }
    button {
      background-color: #4CAF50;
      color: white;
      border: none;
      padding: 10px 20px;
      cursor: pointer;
      border-radius: 5px;
      font-size: 16px;
    }
    button:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Agregar Nueva Reseña</h1>
  <form action="NuevaResenaServlet" method="POST">
    <label for="categoria">Tipo de Comida:</label>
    <!-- Combo Box (Filtro de tipo de comida) -->
    <select id="categoria" name="categoria" required>
      <option value="chatarra">Comida Chatarra</option>
      <option value="tradicional">Comida Tradicional</option>
    </select>

    <label for="restaurante">Nombre del Restaurante:</label>
    <input type="text" id="restaurante" name="restaurante" required>

    <label for="contenido">Tu Reseña:</label>
    <!-- Área de texto con tamaño fijo -->
    <textarea id="contenido" name="contenido" required></textarea>

    <div class="buttons">
      <button type="submit">Publicar</button>
      <!-- Botón Cancelar en lugar de Regresar al Foro -->
      <button type="button" onclick="window.location.href='foro.jsp'">Cancelar</button>
    </div>
  </form>
</div>
</body>
</html>
