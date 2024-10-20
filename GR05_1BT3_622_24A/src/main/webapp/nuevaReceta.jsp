<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Nueva Receta - Las Huequitas</title>
  <style>
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
    form {
      display: flex;
      flex-direction: column;
      gap: 15px;
    }
    label {
      font-weight: bold;
    }
    input[type="text"],
    select,
    textarea {
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
      width: 100%;
      box-sizing: border-box;
    }
    textarea {
      height: 150px;
      resize: none;
    }
    .button-group {
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
    .cancel-button {
      background-color: #f44336;
    }
    .cancel-button:hover {
      background-color: #e53935;
    }
    .error-message {
      color: red;
      font-weight: bold;
      margin-bottom: 15px;
    }
  </style>
</head>
<body>
<div class="container">
  <h1>Agregar Nueva Receta</h1>

  <%-- Mostrar el mensaje de error si existe --%>
  <%
    String errorMessage = (String) request.getAttribute("error");
    if (errorMessage != null) {
  %>
  <div class="error-message"><%= errorMessage %></div>
  <%
    }
  %>

  <form action="AgregarRecetaServlet" method="POST">
    <label for="nombre">Nombre de la Receta:</label>
    <input type="text" id="nombre" name="nombre" required>

    <label for="tipoReceta">Tipo de Receta:</label>
    <select id="tipoReceta" name="tipoReceta" required>
      <option value="postre">Postre</option>
      <option value="entrada">Entrada</option>
      <option value="plato-fuerte">Plato Fuerte</option>
      <option value="ensalada">Ensalada</option>
    </select>

    <label for="ingredientes">Ingredientes:</label>
    <textarea id="ingredientes" name="ingredientes" required></textarea>

    <label for="preparacion">Instrucciones de Preparación:</label>
    <textarea id="preparacion" name="preparacion" required></textarea>

    <div class="button-group">
      <form action="AgregarRecetaServlet" method="POST" style="margin: 0;">
        <button type="submit">Publicar</button>
      </form>
      <form action="RecetaServlet" method="GET" style="margin: 0;">
        <button type="submit" class="cancel-button">Cancelar</button>
      </form>
    </div>
  </form>
</div>
</body>
</html>
