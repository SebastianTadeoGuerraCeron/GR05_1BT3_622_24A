package modelo;

import dao.RecetaJpaController;
import dao.ResenaJpaController;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Foro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Relación uno a muchos entre Foro y Reseña
    @OneToMany(mappedBy = "foro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resena> listaResena = new ArrayList<>(); // Iniciar la lista para evitar NullPointerException

    // Relación uno a muchos entre Foro y Receta
    @OneToMany(mappedBy = "foro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receta> listaReceta = new ArrayList<>();

    // Lista auxiliar para almacenar las recetas recuperadas de la base de datos
    @Transient  // Esta anotación indica que esta lista no será persistida en la base de datos
    private List<Receta> listaAuxRecetas = new ArrayList<>();

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

    public List<Receta> getListaReceta() {
        return listaReceta;
    }

    public void setListaReceta(List<Receta> listaReceta) {
        this.listaReceta = listaReceta;
    }

    // Getters y setters para la lista auxiliar de recetas
    public List<Receta> getListaAuxRecetas() {
        return listaAuxRecetas;
    }

    public void setListaAuxRecetas(List<Receta> listaAuxRecetas) {
        this.listaAuxRecetas = listaAuxRecetas;
    }

    // Método para obtener todas las reseñas
    public List<Resena> mostrarResenas(ResenaJpaController resenaJpaController) {
        // Retorna todas las reseñas desde la base de datos
        return resenaJpaController.findResenaEntities();
    }

    // Método para obtener todas las recetas
    public List<Receta> mostrarRecetas(RecetaJpaController recetaJpaController) {
        // Retorna todas las recetas desde la base de datos
        return recetaJpaController.findRecetaEntities();
    }

    @Override
    public String toString() {
        return "Foro{" +
                "nombre='" + name + '\'' +
                ", listaReceta=" + listaReceta +
                '}';
    }
}
