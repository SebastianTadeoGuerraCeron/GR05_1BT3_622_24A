package modelo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "receta_id")
    private List<ComentarioReceta> comentarios = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "foroReceta_id")
    private ForoReceta foroReceta;

    @OneToOne(cascade = CascadeType.ALL)
    private ReaccionReceta reacciones = new ReaccionReceta();

    private static final ModeradorOfensivo moderador = new ModeradorOfensivo();

    public Receta(String nombre, String tipoReceta, String ingredientes, String preparacion) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.preparacion = preparacion;
        this.tipoReceta = tipoReceta;
    }

    public Receta() {}

    public List<Receta> publicarReceta(ForoReceta foroReceta) {
        this.foroReceta = foroReceta;
        if (foroReceta != null) {
            foroReceta.getListaReceta().add(this);
            return foroReceta.getListaReceta();
        }
        return null;
    }

    public List<Receta> eliminarReceta(ForoReceta foroReceta) {
        if (foroReceta != null) {
            foroReceta.getListaReceta().remove(this);
            return foroReceta.getListaReceta();
        }
        return null;
    }

    // Métodos para agregar y obtener comentarios
    public void agregarComentario(ComentarioReceta comentario) {
        comentarios.add(comentario);
    }

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

    public boolean verificarContenidoOfensivo() {
        return moderador.verificarOfensivo(this.nombre) ||
                moderador.verificarOfensivo(this.tipoReceta) ||
                moderador.verificarOfensivo(this.ingredientes) ||
                moderador.verificarOfensivo(this.preparacion);
    }

    public boolean verificarContenidoMax200() {
        return this.nombre.length() <= 200 &&
                this.tipoReceta.length() <= 200 &&
                this.ingredientes.length() <= 200 &&
                this.preparacion.length() <= 200;
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

    public ForoReceta getForoReceta() {
        return foroReceta;
    }

    public void setForoReceta(ForoReceta foroReceta) {
        this.foroReceta = foroReceta;
    }
}
