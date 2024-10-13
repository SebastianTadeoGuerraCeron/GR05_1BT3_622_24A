package modelo;

import jakarta.persistence.*;
import dao.ResenaJpaController;
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

    // Método para publicar un comentario en una reseña
    public static Comentario publicarComentario(String contenido, Long idResena, ResenaJpaController resenaJpaController) throws Exception {
        // Buscar la reseña asociada en la base de datos
        Resena resena = resenaJpaController.findResena(idResena);

        if (resena == null) {
            throw new Exception("No se pudo encontrar la reseña.");
        }

        // Crear un nuevo comentario
        Comentario nuevoComentario = new Comentario();
        nuevoComentario.setContent(contenido);
        nuevoComentario.setDatePublish(LocalDateTime.now());
        nuevoComentario.setResena(resena);

        // Agregar el comentario a la lista de comentarios de la reseña
        resena.getListaComentarios().add(nuevoComentario);
        resenaJpaController.edit(resena);

        return nuevoComentario;
    }
}
