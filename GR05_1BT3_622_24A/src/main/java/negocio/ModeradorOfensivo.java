package negocio;

// Clase para verificar contenido ofensivo
// Clase para verificar contenido ofensivo
public class ModeradorOfensivo {
    private static final String[] PALABRAS_OFENSIVAS = {
            "puta", "zorra", "mierda", "tonto", "estúpida", "idiota", "imbécil", "feo", "asqueroso", "burro", "tarado", "bobo",
            "adefecioso","malo", "estúpido", "zopenco", "patán", "cretino", "baboso", "loco", "menso", "cobarde",
            "holgazán", "gusano", "mocoso", "caradura", "bruto", "mugroso", "animal", "cerdo",
            "vago", "chismoso", "hipócrita", "mentiroso", "rata", "traidor", "insolente",
            "ignorante", "payaso", "sucio", "maleducado", "necio", "desgraciado", "bastardo"
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


