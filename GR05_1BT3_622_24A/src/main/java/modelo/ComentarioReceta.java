package modelo;

import jakarta.persistence.*;
import negocio.ModeradorOfensivo;
import java.time.LocalDateTime;



@Entity
public class ComentarioReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    private LocalDateTime fechaPublicacion;  // Nuevo campo para la fecha de publicación

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;  // Relación con la clase Receta
    private static final ModeradorOfensivo moderador = new ModeradorOfensivo();

    public ComentarioReceta(String texto, LocalDateTime fechaPublicacion) {
        this.texto = texto;
        this.fechaPublicacion = fechaPublicacion;
    }

    public ComentarioReceta() {
    }

    public ComentarioReceta(String contenido) {
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean verificarContenidoOfensivo() {
        // Verifica si los ingredientes o la preparación contienen palabras ofensivas
        return moderador.verificarOfensivo(texto);
    }
    @Override
    public String toString() {
        return "ComentarioReceta{" +
                "texto='" + texto + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }
}
