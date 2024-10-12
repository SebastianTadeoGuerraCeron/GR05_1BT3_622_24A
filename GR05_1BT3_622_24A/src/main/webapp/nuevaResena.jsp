<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Nueva Reseña - Las Huequitas</title>
</head>
<body>
<h1>Agregar Nueva Reseña</h1>

<form action="NuevaResenaServlet" method="POST">
  <label for="categoria">Tipo de Comida:</label>
  <input type="text" id="categoria" name="categoria" required><br><br>

  <label for="restaurante">Nombre del Restaurante:</label>
  <input type="text" id="restaurante" name="restaurante" required><br><br>

  <label for="contenido">Tu Reseña:</label>
  <textarea id="contenido" name="contenido" required></textarea><br><br>

  <button type="submit">Publicar</button>
</form>
</body>
</html>
