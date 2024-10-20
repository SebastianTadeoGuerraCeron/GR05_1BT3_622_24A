package modelo;

import jakarta.persistence.*;

@Entity
public class ReaccionReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReactionType type;  // Tipo de reacción (LIKE o DISLIKE)

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;  // Relación con el usuario que hizo la reacción

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;  // Relación con la receta a la que pertenece la reacción

    // Constructor sin argumentos
    public ReaccionReceta() {}

    // Constructor con el tipo de reacción y el usuario
    public ReaccionReceta(ReactionType type, Usuario usuario, Receta receta) {
        this.type = type;
        this.usuario = usuario;
        this.receta = receta;
    }

    // Getters y Setters para el tipo de reacción
    public ReactionType getType() {
        return type;
    }

    public void setType(ReactionType type) {
        this.type = type;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    private void actualizarReaccion(boolean isIncrement) {
        this.receta.actualizarReaccion(this.type, isIncrement);
    }

    // Método para incrementar la reacción
    public void incrementReaction() {
        actualizarReaccion(true);
    }

    // Método para eliminar la reacción
    public void removeReaction() {
        actualizarReaccion(false);
    }

    // Getters y Setters para el ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Métodos para obtener los contadores de likes y dislikes
    public int getLikes() {
        return receta.getContadorLikes();
    }

    public int getDislikes() {
        return receta.getContadorDislike();
    }
}