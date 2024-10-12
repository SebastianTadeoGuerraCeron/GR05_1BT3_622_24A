package modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity // Marca esta clase como una entidad JPA
public class Foro {

    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // La clave será autogenerada
    private Long id;

    private String name;

    // Relación uno a muchos entre Foro y Reseña
    @OneToMany(mappedBy = "foro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resena> listaResena;

    // Constructor sin argumentos
    public Foro() {}

    // Constructor con argumentos
    public Foro(String name) {
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

    public List<Resena> getListaResena() {
        return listaResena;
    }

    public void setListaResena(List<Resena> listaResena) {
        this.listaResena = listaResena;
    }
}