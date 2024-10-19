package negocio;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ModeradorOfensivo {
    private static final String[] PALABRAS_OFENSIVAS = {
            "puta", "zorra", "mierda", "tonto", "estúpida", "idiota", "imbécil", "feo", "asqueroso", "burro", "tarado", "bobo",
            "adefecioso", "malo", "estúpido", "zopenco", "patán", "cretino", "baboso", "loco", "menso", "cobarde",
            "holgazán", "gusano", "mocoso", "caradura", "bruto", "mugroso", "animal", "cerdo",
            "vago", "chismoso", "hipócrita", "mentiroso", "rata", "traidor", "insolente",
            "ignorante", "payaso", "sucio", "maleducado", "necio", "desgraciado", "bastardo"
    };

    private static final Pattern PATRON_OFENSIVO;

    static {
        String pattern = String.join("|", PALABRAS_OFENSIVAS);
        PATRON_OFENSIVO = Pattern.compile("\\b(" + pattern + ")\\b", Pattern.CASE_INSENSITIVE);
    }

    public boolean verificarOfensivo(String contenido) {
        if (contenido == null || contenido.isEmpty()) {
            return false;
        }
        Matcher matcher = PATRON_OFENSIVO.matcher(contenido);
        return matcher.find();  // Devuelve true si encuentra una palabra ofensiva
    }
}
