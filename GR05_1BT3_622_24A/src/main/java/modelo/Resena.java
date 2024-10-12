package modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity // Marca esta clase como una entidad JPA
public class Resena {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String resenaID;
    private LocalDateTime fechaPublicacion;
    private String restaurant;
    private String categoria;
    private String contenido;

    // Relación muchos a uno con Foro
    @ManyToOne
    @JoinColumn(name = "foro_id") // Clave foránea en la tabla reseñas que apunta a la tabla foros
    private Foro foro;

    // Relación uno a muchos entre Reseña y Comentario
    @OneToMany(mappedBy = "resena", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> listaComentarios;

    // Constructor sin argumentos
    public Resena() {}

    // Constructor con argumentos
    public Resena(String resenaID, LocalDateTime fechaPublicacion, String restaurant, String categoria, String contenido, Foro foro) {
        this.resenaID = resenaID;
        this.fechaPublicacion = fechaPublicacion;
        this.restaurant = restaurant;
        this.categoria = categoria;
        this.foro = foro;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResenaID() {
        return resenaID;
    }

    public void setResenaID(String resenaID) {
        this.resenaID = resenaID;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public String getContent() {
        return contenido;
    }

    public Foro getForo() {
        return foro;
    }

    public void setForo(Foro foro) {
        this.foro = foro;
    }

    public List<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }


}
