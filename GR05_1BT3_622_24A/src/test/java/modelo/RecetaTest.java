package modelo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
/*
   // @Test
   // public void given_receta_when_comentarReceta_then_comentarReceta() {
        // Crear una receta
       // Receta receta = new Receta("Tarta de manzana", "Postre", "Manzana, azúcar, harina", "Mezclar y hornear");

        // Agregar comentarios a la receta
        //ComentarioReceta comentario1 = new ComentarioReceta("Muy buena receta, la recomiendo!");
       // ComentarioReceta comentario2 = new ComentarioReceta("Me encantó, pero cambié algunos ingredientes.");

       // receta.agregarComentario(comentario1);
       // receta.agregarComentario(comentario2);

        // Verificar que los comentarios se agregaron correctamente
       // <ComentarioReceta> comentarios = receta.getComentarios();
       //assertEquals(2, comentarios.size());
       // assertEquals("Muy buena receta, la recomiendo!", comentarios.get(0).getTexto());
       // assertEquals("Me encantó, pero cambié algunos ingredientes.", comentarios.get(1).getTexto());

    //}

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

    @Test
    public void given_receta_when_agregarLike_then_agregarReaction() {
        // Crear una receta
        Receta receta = new Receta("Tarta de manzana", "Postre", "Manzana, azúcar, harina", "Mezclar y hornear");

        // Inicialmente, la receta no debe tener likes
        assertEquals(0, receta.getReacciones().getLikes());

        // Agregar 3 likes
        receta.getReacciones().agregarLike();
        receta.getReacciones().agregarLike();
        receta.getReacciones().agregarLike();

        // Verificar que los likes se incrementaron correctamente
        assertEquals(3, receta.getReacciones().getLikes());

        // Restar un like y verificar
       // receta.getReacciones().restarLike();
      //  assertEquals(2, receta.getReacciones().getLikes());
   // }

    @Test
    public void given_receta_when_agregarDislike_then_agregarReaction() {
        // Crear una receta
        Receta receta = new Receta("Tarta de manzana", "Postre", "Manzana, azúcar, harina", "Mezclar y hornear");

        // Inicialmente, la receta no debe tener dislikes
        assertEquals(0, receta.getReacciones().getDislikes());

        // Agregar 2 dislikes
        receta.getReacciones().agregarDislike();
        receta.getReacciones().agregarDislike();

        // Verificar que los dislikes se incrementaron correctamente
        assertEquals(2, receta.getReacciones().getDislikes());

        // Restar un dislike y verificar
        receta.getReacciones().restarDislike();
        assertEquals(1, receta.getReacciones().getDislikes());
    }

*/
    @ParameterizedTest
    @CsvSource({
            "'Torta de rata', 'Harina', 'Mezclar todo y hornear'",  // Palabra ofensiva en el nombre
            "'Torta', 'Harina de rata', 'Mezclar todo y hornear'",  // Palabra ofensiva en los ingredientes
            "'Torta', 'Harina', 'Mezclar todo como un idiota'",  // Palabra ofensiva en la preparación
            "'Torta estúpida', 'Harina', 'Mezclar todo y hornear'"  // Palabra ofensiva en el nombre
    })
    public void givenRecetaConPalabrasOfensivas_whenVerificarContenidoOfensivo_thenRetornaTrue(String nombre, String ingredientes, String preparacion) {
        // Arrange: Crear una receta con palabras ofensivas en diferentes partes
        Receta receta = new Receta(nombre, "Postre", ingredientes, preparacion);

        // Act: Verificar si la receta contiene palabras ofensivas
        boolean resultado = receta.verificarContenidoOfensivo();

        // Assert: El resultado debe ser true porque alguna parte contiene una palabra ofensiva
        assertTrue(resultado);
    }

    @ParameterizedTest
    @CsvSource({
            "'Tarta de manzana', 'Postre', 'Manzana, azúcar, harina, canela', 'Mezclar los ingredientes y hornear durante 30 minutos'",
            "'Pastel de 5 pisos con decoración espectacular', 'Pastel de boda', 'Harina, azúcar, mantequilla, huevos, crema', 'Mezclar todo y hornear por partes, luego montar y decorar con crema'",
            "'Pan de chocolate con relleno de crema y trozos de almendra caramelizada', 'Dulce', 'Harina, azúcar, cacao, almendras', 'Preparar la masa, hornear y rellenar con crema antes de servir'",
            "'Pizza cuatro quesos', 'Comida italiana', 'Mozzarella, Gorgonzola, Parmesano, Ricotta', 'Preparar la masa, agregar los quesos y hornear a 200 grados'",
            "'Ensalada de frutas con aderezo de yogurt natural', 'Ensalada', 'Frutas frescas, yogurt natural, miel, nueces', 'Cortar las frutas, mezclar con yogurt y miel, agregar nueces al final'",
            "'Churrasco ecuatoriano', 'Comida típica', 'Carne, arroz, huevo frito, aguacate', 'Cocinar la carne al gusto, acompañar con arroz, huevo frito y aguacate'"
    })

    public void given_receta_when_fieldsLessThan200Chars_then_validReceta(String nombre, String tipoReceta, String ingredientes, String preparacion) {
        Receta receta = new Receta(nombre, tipoReceta, ingredientes, preparacion);
        boolean resultado = receta.verificarContenidoMax200();
        assertTrue(resultado);
    }



}

