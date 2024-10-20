package modelo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ForoReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Relación uno a muchos entre Foro y Receta
    @OneToMany(mappedBy = "foroReceta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receta> listaReceta = new ArrayList<>();

    // Constructor sin argumentos
    public ForoReceta() {}

    // Constructor con argumentos
    public ForoReceta(String name) {
        this.name = name;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Receta> getListaReceta() {
        return listaReceta;
    }

    public void setListaReceta(List<Receta> listaReceta) {
        this.listaReceta = listaReceta;
    }

    @Override
    public String toString() {
        return "ForoReceta{" +
                "name='" + name + '\'' +
                ", listaReceta=" + listaReceta +
                '}';
    }
}
