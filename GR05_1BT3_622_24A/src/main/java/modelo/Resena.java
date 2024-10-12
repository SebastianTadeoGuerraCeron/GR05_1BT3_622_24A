package modelo;

import java.util.List;
import java.time.LocalDateTime;

public class Resena {
    private String resenaID;
    private LocalDateTime fechaPublicacion;
    private String restaurant;
    private String categoria;
    private String content;
    private List<Comentario> listaComentarios;

    public Resena(String resenaID, LocalDateTime fechaPublicacion, String restaurant, String categoria, String content, List<Comentario> listaComentarios) {
        this.resenaID = resenaID;
        this.fechaPublicacion = fechaPublicacion;
        this.restaurant = restaurant;
        this.categoria = categoria;
        this.content = content;
        this.listaComentarios = listaComentarios;
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
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comentario> getListaComentarios() {
        return listaComentarios;
    }

    public void setListaComentarios(List<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }
}

