package modelo;

import java.time.LocalDateTime;

public class Comentario {
    private String commentID;
    private String content;
    private LocalDateTime datePublish;

    public Comentario(String commentID, String content, LocalDateTime datePublish) {
        this.commentID = commentID;
        this.content = content;
        this.datePublish = datePublish;
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
}

