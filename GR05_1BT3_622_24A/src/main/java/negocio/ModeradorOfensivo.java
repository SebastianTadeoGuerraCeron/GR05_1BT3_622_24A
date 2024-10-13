package negocio;

// Clase para verificar contenido ofensivo
// Clase para verificar contenido ofensivo
public class ModeradorOfensivo {
    private static final String[] PALABRAS_OFENSIVAS = {
            "tonto", "estúpida", "idiota", "imbécil", "feo", "asqueroso", // Añade más palabras aquí
    };

    public boolean verificarOfensivo(String contenido) {
        for (String palabra : PALABRAS_OFENSIVAS) {
            if (contenido.toLowerCase().contains(palabra.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}


