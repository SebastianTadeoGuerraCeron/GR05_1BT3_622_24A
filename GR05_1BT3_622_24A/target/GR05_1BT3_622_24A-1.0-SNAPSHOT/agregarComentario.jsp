<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Comentario - Las Huequitas</title>
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
        .button-group {
            display: flex;
            justify-content: flex-start; /* Alinea los botones hacia la izquierda */
            gap: 460px; /* Añade espacio entre los botones */
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
    </style>
</head>
<body>
<div class="container">
    <h1>Agregar Comentario</h1>

    <!-- Formulario para agregar el comentario -->
    <form action="AgregarComentarioServlet" method="POST">
        <!-- El ID de la reseña al que pertenece el comentario se envía como un campo oculto -->
        <input type="hidden" name="idResena" value="<%= request.getParameter("id") %>" />

        <label for="contenido">Tu Comentario:</label>
        <textarea id="contenido" name="contenido" required></textarea>

        <!-- Grupo de botones alineados horizontalmente -->
        <div class="button-group">
            <!-- Formulario para el botón "Publicar" -->
            <form action="AgregarComentarioServlet" method="POST" style="margin: 0;">
                <button type="submit">Publicar</button>
            </form>

            <!-- Formulario para el botón "Cancelar" -->
            <form action="ForoServlet" method="GET" style="margin: 0;">
                <button type="submit" class="cancel-button">Cancelar</button>
            </form>
        </div>
    </form>
</div>
</body>
</html>
