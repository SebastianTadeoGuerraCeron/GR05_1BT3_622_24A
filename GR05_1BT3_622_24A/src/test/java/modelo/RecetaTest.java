package modelo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecetaTest {
    Foro foro = null;
    Receta receta = null;

    @BeforeEach
    public void setUp(){
        System.out.println("SetUp");
        foro = new Foro("Foro de recetas");
        receta = new Receta("Torta de chocolate", "Postre", "Harina, azúcar, cacao, huevos", "Mezclar los ingredientes y hornear");
    }
    @Test
    public void given_receta_when_publicarReceta_then_nuevaReceta(){
        System.out.println("Test publicarReceta");
        receta.publicarReceta(foro);
        System.out.println(foro.getListaReceta());
        //assertEquals(1, foro.getListaReceta().size());
        assertTrue(foro.getListaReceta().contains(receta));
    }

    @Test
    public void given_receta_when_eliminarReceta_then_quitarReceta(){
        System.out.println("Test eliminarReceta");
        receta.publicarReceta(foro);
        System.out.println( "Before " + foro.getListaReceta());
        receta.eliminarReceta(foro);
        System.out.println("After "+ foro.getListaReceta());
        assertFalse(foro.getListaReceta().contains(receta));
    }

    @Test
    public void given_receta_when_comentarReceta_then_comentarReceta() {
        // Crear una receta
        Receta receta = new Receta("Tarta de manzana", "Postre", "Manzana, azúcar, harina", "Mezclar y hornear");

        // Agregar comentarios a la receta
        ComentarioReceta comentario1 = new ComentarioReceta("Muy buena receta, la recomiendo!");
        ComentarioReceta comentario2 = new ComentarioReceta("Me encantó, pero cambié algunos ingredientes.");

        receta.agregarComentario(comentario1);
        receta.agregarComentario(comentario2);

        // Verificar que los comentarios se agregaron correctamente
        List<ComentarioReceta> comentarios = receta.getComentarios();
        assertEquals(2, comentarios.size());
        assertEquals("Muy buena receta, la recomiendo!", comentarios.get(0).getTexto());
        assertEquals("Me encantó, pero cambié algunos ingredientes.", comentarios.get(1).getTexto());

    }

    @Test
    public void given_receta_when_eliminarComentario_then_quitarComentarioReceta() {
        // Crear una receta
        Receta receta = new Receta("Tarta de manzana", "Postre", "Manzana, azúcar, harina", "Mezclar y hornear");

        // Agregar comentarios a la receta
        ComentarioReceta comentario1 = new ComentarioReceta("Muy buena receta, la recomiendo!");
        ComentarioReceta comentario2 = new ComentarioReceta("Me encantó, pero cambié algunos ingredientes.");
        receta.agregarComentario(comentario1);
        receta.agregarComentario(comentario2);

        // Verificar que hay 2 comentarios
        List<ComentarioReceta> comentarios = receta.getComentarios();
        assertEquals(2, comentarios.size());

        // Eliminar el primer comentario
        boolean eliminado = receta.eliminarComentario(comentario1);

        // Verificar que el comentario fue eliminado
        assertTrue(eliminado);
        assertEquals(1, comentarios.size());
        assertEquals("Me encantó, pero cambié algunos ingredientes.", comentarios.get(0).getTexto());
    }
}