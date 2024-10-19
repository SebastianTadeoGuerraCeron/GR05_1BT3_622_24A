package negocio;

import modelo.Foro;
import modelo.Receta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FiltroTest {
    private Foro foro;

    @BeforeEach
    public void setUp() {
        // Creamos un foro vacío antes de cada prueba
        foro = new Foro("Foro de Recetas");
    }

    @Test
    public void givenListOfRecetas_whenFiltrarPorTipoReceta_thenReturnFilteredRecetas() {
        // Arrange
        Receta receta1 = new Receta("Torta de chocolate", "Postre", "Harina, azúcar, cacao, huevos", "Mezclar todo y hornear");
        Receta receta2 = new Receta("Ensalada César", "Entrada", "Lechuga, crutones, queso parmesano, aderezo", "Mezclar todo");
        Receta receta3 = new Receta("Lasagna", "Plato fuerte", "Pasta, salsa de tomate, queso, carne", "Armar y hornear");

        // Publicamos las recetas en el foro
        receta1.publicarReceta(foro);
        receta2.publicarReceta(foro);
        receta3.publicarReceta(foro);

        // Act: Filtramos por tipo de receta "Postre"
        List<Receta> postres = Filtro.filtrarPorTipoReceta(foro.getListaReceta(), "Postre");

        // Assert: Verificamos que solo hay 1 postre
        assertEquals(1, postres.size());
        assertEquals("Torta de chocolate", postres.get(0).getNombre());
    }

}