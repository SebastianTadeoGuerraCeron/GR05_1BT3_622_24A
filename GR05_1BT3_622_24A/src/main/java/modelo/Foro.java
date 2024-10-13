package modelo;

import dao.ResenaJpaController;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Foro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // Método para obtener todas las reseñas
    public List<Resena> mostrarResenas(ResenaJpaController resenaJpaController) {
        // Retorna todas las reseñas desde la base de datos
        return resenaJpaController.findResenaEntities();
    }
}
