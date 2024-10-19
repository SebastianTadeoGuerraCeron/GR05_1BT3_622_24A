package negocio;

import modelo.Foro;
import modelo.Receta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

    @Test
    public void givenListOfRecetas_whenFiltrarPorNombreReceta_thenReturnFilteredRecetas() {
        // Crear una lista simulada de recetas
        List<Receta> recetas = new ArrayList<>();
        recetas.add(new Receta("Tarta de chocolate", "Postre", "Chocolate, harina, azúcar", "Mezclar y hornear"));
        recetas.add(new Receta("Pollo al horno", "Plato principal", "Pollo, sal, pimienta", "Hornear el pollo"));
        recetas.add(new Receta("Ensalada verde", "Entrada", "Lechuga, pepino, tomate", "Mezclar los ingredientes"));
        recetas.add(new Receta("Pollo con salsa agridulce", "Entrada", "Lechuga, pepino, tomate", "Mezclar los ingredientes"));
        recetas.add(new Receta("Salsa agridulce", "Entrada", "Lechuga, pepino, tomate", "Mezclar los ingredientes"));
        recetas.add(new Receta("Lechuga remojada en salsa agridulce", "Entrada", "Lechuga, pepino, tomate", "Mezclar los ingredientes"));
        recetas.add(new Receta("Chancho asado", "Entrada", "Lechuga, pepino, tomate", "Mezclar los ingredientes"));


        // Realizar la búsqueda con un filtro que debe devolver una coincidencia parcial
        List<Receta> resultado = Filtro.obtenerYFiltrarRecetasPorNombre("chancho con salsa agridulce", recetas);

        // Verificar que el tamaño del resultado es el esperado
        assertEquals(4, resultado.size());
        System.out.println(resultado.get(0).getNombre());
        System.out.println(resultado.get(1).getNombre());
        System.out.println(resultado.get(2).getNombre());
        System.out.println(resultado.get(3).getNombre());

        // Verificar que el nombre de la receta es el esperado
        assertEquals("Pollo con salsa agridulce", resultado.get(0).getNombre());
    }
}