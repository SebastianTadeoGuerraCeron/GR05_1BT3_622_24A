package negocio;

public class ModeradorComplete {
    public boolean verificarComplete(String categoria, String restaurante, String contenido) {
        boolean categoriaValida = categoria != null && !categoria.isEmpty();
        boolean restauranteValido = restaurante != null && !restaurante.isEmpty();
        boolean contenidoValido = contenido != null && !contenido.isEmpty();

        return categoriaValida && restauranteValido && contenidoValido;
    }

    // Comentario Completo clase para verificar
    public static boolean esComentarioValido(String comentario) {
        return comentario != null && !comentario.trim().isEmpty();
    }
}