package negocio;

import dao.ResenaJpaController;
import modelo.Foro;
import modelo.Receta;
import modelo.Resena;

import java.util.List;
import java.util.stream.Collectors;

public class Filtro {

    public static List<Resena> obtenerYFiltrarResenas(String filtroCategoria, ResenaJpaController resenaJpaController) {
        // Crear una instancia de Foro
        Foro foro = new Foro("Foro de Reseñas");

        // Obtener la lista de reseñas desde la clase Foro
        List<Resena> listaResenas = foro.mostrarResenas(resenaJpaController);

        // Filtrar la lista de reseñas según la categoría seleccionada
        String categoriaFiltroT = filtroCategoria; // Use a local variable instead of modifying the parameter
        return filtrarPorCategoria(listaResenas, categoriaFiltroT);
    }

    public static List<Resena> filtrarPorCategoria(List<Resena> listaResenas, String categoria) {
        if (categoria == null || categoria.equalsIgnoreCase("ALL")) {
            return listaResenas; // Si es "ALL" o nulo, devuelve la lista completa.
        }

        return listaResenas.stream()
                .filter(resena -> resena.getCategoria().equalsIgnoreCase(categoria))
                .collect(Collectors.toList()); // Filtra por categoría.
    }

    public static List<Receta> filtrarPorTipoReceta(List<Receta> listaRecetas, String tipoReceta) {
        if (tipoReceta == null || tipoReceta.equalsIgnoreCase("ALL")) {
            return listaRecetas; // Si es "ALL" o nulo, devuelve la lista completa.
        }

        return listaRecetas.stream()
                .filter(receta -> receta.getTipoReceta().equalsIgnoreCase(tipoReceta))
                .collect(Collectors.toList()); // Filtra por tipo de receta.
    }
}