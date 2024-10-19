package modelo;

import jakarta.persistence.*;

@Entity
public class ComentarioReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texto;

    public ComentarioReceta(String texto) {
        this.texto = texto;
    }

    public ComentarioReceta() {
    }

    public String getTexto() {
        return texto;
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
}
