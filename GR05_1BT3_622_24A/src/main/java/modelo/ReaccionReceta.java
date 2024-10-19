package modelo;

import jakarta.persistence.*;

@Entity
public class ReaccionReceta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int likes = 0;
    private int dislikes = 0;

    // Métodos para agregar y restar likes
    public void agregarLike() {
        this.likes++;
    }

    public void restarLike() {
        if (this.likes > 0) {
            this.likes--;
        }
    }

    // Métodos para agregar y restar dislikes
    public void agregarDislike() {
        this.dislikes++;
    }

    public void restarDislike() {
        if (this.dislikes > 0) {
            this.dislikes--;
        }
    }

    // Getters para likes y dislikes
    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    @Override
    public String toString() {
        return "ReaccionReceta{" +
                "likes=" + likes +
                ", dislikes=" + dislikes +
                '}';
    }
}
