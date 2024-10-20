package modelo;

import jakarta.persistence.*;
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

    public ComentarioReceta(String texto, LocalDateTime fechaPublicacion) {
        this.texto = texto;
        this.fechaPublicacion = fechaPublicacion;
    }

    public ComentarioReceta() {
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

    @Override
    public String toString() {
        return "ComentarioReceta{" +
                "texto='" + texto + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }
}
