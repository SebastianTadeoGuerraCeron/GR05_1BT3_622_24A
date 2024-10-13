package negocio;

import modelo.Resena;
import java.util.List;
import java.util.stream.Collectors;

public class Filtro {

    /**
     * Filtra la lista de reseñas por categoría.
     * @param listaResenas La lista completa de reseñas.
     * @param categoria La categoría seleccionada para el filtro.
     * @return Una lista filtrada de reseñas, o la lista completa si la categoría es "ALL".
     */
    public static List<Resena> filtrarPorCategoria(List<Resena> listaResenas, String categoria) {
        if (categoria == null || categoria.equalsIgnoreCase("ALL")) {
            return listaResenas; // Si es "ALL" o nulo, devuelve la lista completa.
        }

        return listaResenas.stream()
                .filter(resena -> resena.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList()); // Filtra por categoría.
    }

}


