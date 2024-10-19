package modelo;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipoReceta;
    private String ingredientes;
    private String preparacion;

    @ManyToOne
    @JoinColumn(name = "foro_id")
    private Foro foro;

    public Receta(String nombre, String tipoReceta, String ingredientes, String preparacion) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.preparacion = preparacion;
        this.tipoReceta = tipoReceta;
    }
    public Receta() {
    }

    public List<Receta> publicarReceta(Foro foro) {
        this.foro = foro;
        if(foro != null) {
            foro.getListaReceta().add(this);
            return foro.getListaReceta();
        }
        return null;
    }

    public List<Receta> eliminarReceta(Foro foro) {
       if(foro != null) {
           foro.getListaReceta().remove(this);
           return foro.getListaReceta();
       }
         return null;
    }

    @Override
    public String toString() {
        return "Receta{" +
                "nombre='" + nombre + '\'' +
                ", tipoReceta='" + tipoReceta + '\'' +
                ", ingredientes='" + ingredientes + '\'' +
                ", instrucciones='" + preparacion + '\'' +
                '}';
    }


    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public String getTipoReceta() {
        return tipoReceta;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipoReceta(String tipoReceta) {
        this.tipoReceta = tipoReceta;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    public Foro getForo() {
        return foro;
    }

    public void setForo(Foro foro) {
        this.foro = foro;
    }


}
