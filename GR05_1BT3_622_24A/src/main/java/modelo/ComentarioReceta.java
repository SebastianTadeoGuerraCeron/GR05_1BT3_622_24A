package modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class ComentarioReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;


    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;  // Relación con la clase Receta


    public ComentarioReceta(String texto) {
        this.texto = texto;
    }

    public ComentarioReceta() {
    }

    public String getTexto() {
        return texto;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "ComentarioReceta{" +
                "texto='" + texto + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
