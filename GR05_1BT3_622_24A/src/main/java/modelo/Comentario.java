package modelo;

import java.time.LocalDateTime;

import dao.ResenaJpaController;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String commentID;
    private String content;
    private LocalDateTime datePublish;

    @ManyToOne
    @JoinColumn(name = "resena_id")
    private Resena resena;


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

    public static Comentario publicarComentario(String contenido, Long idResena,
                                                ResenaJpaController resenaJpaController) throws Exception {
        Resena resena = obtenerResena(idResena, resenaJpaController);
        Comentario nuevoComentario = crearComentario(contenido, resena);
        actualizarResena(resena, nuevoComentario, resenaJpaController);
        return nuevoComentario;
    }

    private static Resena obtenerResena(Long idResena, ResenaJpaController resenaJpaController) throws Exception {
        Resena resena = resenaJpaController.findResena(idResena);
        if (resena == null) {
            throw new Exception("No se pudo encontrar la reseña.");
        }
        return resena;
    }

    private static Comentario crearComentario(String contenido, Resena resena) {
        Comentario nuevoComentario = new Comentario();
        nuevoComentario.setContent(contenido);
        nuevoComentario.setDatePublish(LocalDateTime.now());
        nuevoComentario.setResena(resena);
        return nuevoComentario;
    }

    private static void actualizarResena(Resena resena, Comentario nuevoComentario,
                                         ResenaJpaController resenaJpaController) throws Exception {
        resena.getListaComentarios().add(nuevoComentario);
        resenaJpaController.edit(resena);
    }
}