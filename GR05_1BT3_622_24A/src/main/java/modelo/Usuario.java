package modelo;

import dao.ComentarioJpaController;
import dao.ResenaJpaController;
import dao.ReactionJpaController;
import dao.UsuarioJpaController;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userID;

    // Constructor sin argumentos
    public Usuario() {}

    // Constructor con argumentos
    public Usuario(String userID) {
        this.userID = userID;
    }

    // Método para crear una reseña
    public Resena createResena(String categoria, String restaurante, String contenido, ResenaJpaController resenaJpaController) throws Exception {
        // Llamar al método estático publicarResena en la clase Resena
        Resena nuevaResena = Resena.publicarResena(categoria, restaurante, contenido);

        // Guardar la reseña en la base de datos usando el JPA Controller
        resenaJpaController.create(nuevaResena);

        return nuevaResena;
    }

    // Método para agregar un comentario a una reseña
    public Comentario createComment(String contenido, Long idResena, ComentarioJpaController comentarioJpaController, ResenaJpaController resenaJpaController) throws Exception {
        // Buscar la reseña correspondiente
        Resena resena = resenaJpaController.findResena(idResena);
        if (resena == null) {
            throw new Exception("Reseña no encontrada");
        }

        // Crear y agregar el comentario a la reseña
        Comentario nuevoComentario = new Comentario();
        nuevoComentario.setContent(contenido);
        nuevoComentario.setDatePublish(LocalDateTime.now());
        nuevoComentario.setResena(resena);

        comentarioJpaController.create(nuevoComentario);
        return nuevoComentario;
    }

    // Método para reaccionar (like o dislike) a una reseña
    public void reaccionar(ReactionType tipo, Long idResena, ResenaJpaController resenaJpaController, ReactionJpaController reactionJpaController, UsuarioJpaController usuarioJpaController) throws Exception {
        // Buscar la reseña correspondiente
        Resena resena = resenaJpaController.findResena(idResena);
        if (resena == null) {
            throw new Exception("Reseña no encontrada");
        }

        // Cargar el usuario desde la base de datos (no crear un nuevo Usuario)
        Usuario usuarioPersistente = usuarioJpaController.findUsuario(this.getId());
        if (usuarioPersistente == null) {
            throw new Exception("Usuario no encontrado");
        }

        // Crear la reacción
        Reaccion reaccion = new Reaccion(tipo, usuarioPersistente, resena);  // Asegurarse de que el Usuario sea persistente
        reaccion.incrementReaction(); // Actualizar la reseña (incrementar likes o dislikes)
        reactionJpaController.create(reaccion);
    }


    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
