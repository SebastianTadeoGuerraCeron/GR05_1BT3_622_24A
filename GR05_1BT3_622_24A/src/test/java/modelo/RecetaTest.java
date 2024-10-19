package modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecetaTest {

    @Test
    public void given_receta_when_publicarReceta_then_nuevaReceta(){
        Foro foro = new Foro("Foro de recetas");
        Receta receta = new Receta("Torta de chocolate", "Postre", "Harina, azúcar, cacao, huevos", "Mezclar los ingredientes y hornear");
        receta.publicarReceta(foro);
        System.out.println(foro.getListaReceta());
        //assertEquals(1, foro.getListaReceta().size());
        assertTrue(foro.getListaReceta().contains(receta));
    }

    @Test
    public void given_receta_when_eliminarReceta_then_quitarReceta(){
        Foro foro = new Foro("Foro de recetas");
        Receta receta = new Receta("Torta de chocolate", "Postre", "Harina, azúcar, cacao, huevos", "Mezclar los ingredientes y hornear");
        receta.publicarReceta(foro);
        System.out.println( "Before" +foro.getListaReceta());
        receta.eliminarReceta(foro);
        System.out.println("After"+ foro.getListaReceta());
        assertFalse(foro.getListaReceta().contains(receta));
    }
}
