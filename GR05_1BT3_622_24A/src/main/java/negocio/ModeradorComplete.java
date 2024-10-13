// Clase para verificar si la reseña está completa
package negocio;

// Clase para verificar si la reseña está completa
public class ModeradorComplete {
    public boolean verificarComplete(String categoria, String restaurante, String contenido) {
        return categoria != null && !categoria.isEmpty() &&
                restaurante != null && !restaurante.isEmpty() &&
                contenido != null && !contenido.isEmpty();
    }

    // Comentario Completo clase para verificar
    public static boolean esComentarioValido(String comentario) {
        return comentario != null && !comentario.trim().isEmpty();
    }
}
