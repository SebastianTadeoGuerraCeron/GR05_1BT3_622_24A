package modelo;

import jakarta.persistence.*;

@Entity
public class Reaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReactionType type;  // Tipo de reacción (LIKE o DISLIKE)

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;  // Relación con el usuario que hizo la reacción

    @ManyToOne
    @JoinColumn(name = "resena_id")
    private Resena resena;  // Relación con la reseña a la que pertenece la reacción

    // Constructor sin argumentos
    public Reaccion() {}

    // Constructor con el tipo de reacción y el usuario
    public Reaccion(ReactionType type, Usuario usuario, Resena resena) {
        this.type = type;
        this.usuario = usuario;
        this.resena = resena;
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

    public Resena getResena() {
        return resena;
    }

    public void setResena(Resena resena) {
        this.resena = resena;
    }


    private void actualizarReaccion(boolean isIncrement) {
        if (this.type == ReactionType.LIKE) {
            if (isIncrement) {
                this.resena.aumentarLikes();
            } else {
                this.resena.disminuirLikes();
            }
        } else if (this.type == ReactionType.DISLIKE) {
            if (isIncrement) {
                this.resena.aumentarDislikes();
            } else {
                this.resena.disminuirDislikes();
            }
        }
        System.out.println("Reacción " + type + (isIncrement ? " incrementada." : " eliminada."));
    }

    public void incrementReaction() {
        actualizarReaccion(true);
    }

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
}