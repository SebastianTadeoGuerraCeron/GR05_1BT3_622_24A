package modelo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

}