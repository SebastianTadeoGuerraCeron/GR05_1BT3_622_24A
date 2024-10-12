package modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commentID;
    private String content;
    private LocalDateTime datePublish;

    // Relación muchos a uno con Reseña
    @ManyToOne
    @JoinColumn(name = "resena_id") // Clave foránea que apunta a la tabla reseñas
    private Resena resena;

    // Constructor sin argumentos
    public Comentario() {}

    // Constructor con argumentos
    public Comentario(String commentID, String content, LocalDateTime datePublish, Resena resena) {
        this.commentID = commentID;
        this.content = content;
        this.datePublish = datePublish;
        this.resena = resena;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDatePublish() {
        return datePublish;
    }

    public void setDatePublish(LocalDateTime datePublish) {
        this.datePublish = datePublish;
    }

    public Resena getResena() {
        return resena;
    }

    public void setResena(Resena resena) {
        this.resena = resena;
    }
}
