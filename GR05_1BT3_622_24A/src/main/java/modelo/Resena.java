package modelo;

import java.time.LocalDateTime;

public class Resena {
    private String resenaID;
    private String categoria;
    private String restaurant;
    private String contenido;
    private LocalDateTime fechaPublicacion;

    // Getters y Setters

    public String getResenaID() {
        return resenaID;
    }

    public void setResenaID(String resenaID) {
        this.resenaID = resenaID;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
}

