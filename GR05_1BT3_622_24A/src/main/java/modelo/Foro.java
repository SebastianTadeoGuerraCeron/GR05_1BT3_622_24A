package modelo;

import dao.ResenaJpaController;
import jakarta.persistence.*;
import negocio.Filtro;

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
    private List<Resena> listaResena;

    // Relación uno a muchos entre Foro y Receta
    @OneToMany(mappedBy = "foro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receta> listaReceta = new ArrayList<>();

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

    // Método para obtener todas las reseñas
    public List<Resena> mostrarResenas(ResenaJpaController resenaJpaController) {
        // Retorna todas las reseñas desde la base de datos
        return resenaJpaController.findResenaEntities();
    }

    // Método para obtener todas las recetas asociadas al foro y filtrarlas
    public List<Receta> mostrarRecetas() {
        // Retorna la lista de recetas almacenada en el foro
        return this.listaReceta;
    }

    @Override
    public String toString() {
        return "Foro{" +
                "nombre='" + name + '\'' +
                ", listaReceta=" + listaReceta +
                '}';
    }

}