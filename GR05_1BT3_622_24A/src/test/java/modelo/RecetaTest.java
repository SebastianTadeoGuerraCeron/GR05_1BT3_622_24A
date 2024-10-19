package modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecetaTest {

    @Test
    public void given_receta_when_publicarReceta_then_nuevaReceta() throws Exception{
        // Arrange
        Receta receta = new Receta("Torta de chocolate", "Postre", "Harina, azúcar, cacao, huevos, leche", "Mezclar los ingredientes y hornear");

        // Act
        Receta nuevaReceta = Receta.publicarReceta(receta.getNombre(), receta.getTipoReceta(), receta.getIngredientes(), receta.getPreparacion());

        // Assert
        assertNotNull(nuevaReceta);
    }
}
