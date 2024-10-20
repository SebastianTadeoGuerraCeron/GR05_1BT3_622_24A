package modelo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import modelo.ComentarioReceta;
import negocio.ModeradorOfensivo;

@Entity
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipoReceta;
    private String ingredientes;
    private String preparacion;

    private int contadorLikes;   // Atributo para contar los likes
    private int contadorDislike; // Atributo para contar los dislikes

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "receta_id")
    private List<ComentarioReceta> comentarios = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "foro_id")
    private Foro foro;

    @OneToOne(cascade = CascadeType.ALL)
    private ReaccionReceta reacciones = new ReaccionReceta();

    private static final ModeradorOfensivo moderador = new ModeradorOfensivo();

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
        if (foro != null) {
            foro.getListaReceta().add(this);
            return foro.getListaReceta();
        }
        return null;
    }

    public List<Receta> eliminarReceta(Foro foro) {
        if (foro != null) {
            foro.getListaReceta().remove(this);
            return foro.getListaReceta();
        }
        return null;
    }

    public void actualizarReaccion(ReactionType type, boolean isIncrement) {
        if (type == ReactionType.LIKE) {
            if (isIncrement) {
                aumentarLikes();
            } else {
                disminuirLikes();
            }
        } else if (type == ReactionType.DISLIKE) {
            if (isIncrement) {
                aumentarDislikes();
            } else {
                disminuirDislikes();
            }
        }
        System.out.println("Reacción " + type + (isIncrement ? " incrementada." : " eliminada."));
    }


    // Métodos para agregar y obtener comentarios
    public void agregarComentario(ComentarioReceta comentario) {
        comentarios.add(comentario);
    }

    // Método para eliminar un comentario por referencia
    public boolean eliminarComentario(ComentarioReceta comentario) {
        return comentarios.remove(comentario);
    }

    public List<ComentarioReceta> getComentarios() {
        return comentarios;
    }

    public ReaccionReceta getReacciones() {
        return reacciones;
    }

    public void setReacciones(ReaccionReceta reacciones) {
        this.reacciones = reacciones;
    }

    // Método para verificar si la receta contiene palabras ofensivas
    public boolean verificarContenidoOfensivo() {
        return moderador.verificarOfensivo(this.nombre) ||
                moderador.verificarOfensivo(this.tipoReceta) ||
                moderador.verificarOfensivo(this.ingredientes) ||
                moderador.verificarOfensivo(this.preparacion);
    }


    private boolean esMenorOIgualA200(String texto) {
        return texto.length() <= 200;
    }

    public boolean verificarContenidoMax200() {
        return esMenorOIgualA200(this.nombre) &&
                esMenorOIgualA200(this.tipoReceta) &&
                esMenorOIgualA200(this.ingredientes) &&
                esMenorOIgualA200(this.preparacion);
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

    // Getters y Setters para likes y dislikes
    public int getContadorLikes() {
        return contadorLikes;
    }

    public void setContadorLikes(int contadorLikes) {
        this.contadorLikes = contadorLikes;
    }

    public int getContadorDislike() {
        return contadorDislike;
    }

    public void setContadorDislike(int contadorDislike) {
        this.contadorDislike = contadorDislike;
    }

    // Métodos para aumentar y disminuir likes y dislikes
    public void aumentarLikes() {
        this.contadorLikes++;
    }

    public void disminuirLikes() {
        if (this.contadorLikes > 0) {
            this.contadorLikes--;
        }
    }

    public void aumentarDislikes() {
        this.contadorDislike++;
    }

    public void disminuirDislikes() {
        if (this.contadorDislike > 0) {
            this.contadorDislike--;
        }
    }

    // Getters y Setters generales
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoReceta() {
        return tipoReceta;
    }

    public void setTipoReceta(String tipoReceta) {
        this.tipoReceta = tipoReceta;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparacion() {
        return preparacion;
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
