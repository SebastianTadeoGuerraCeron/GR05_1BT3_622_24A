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

    public static List<Receta> obtenerYFiltrarRecetasPorNombre(String filtroNombre, List<Receta> listaRecetas) {
        // Filtrar la lista de recetas según las palabras en el nombre
        return filtrarPorNombre(listaRecetas, filtroNombre);
    }

    public static List<Receta> filtrarPorNombre(List<Receta> listaRecetas, String filtroNombre) {
        if (filtroNombre == null || filtroNombre.trim().isEmpty()) {
            return listaRecetas; // Si no hay filtro de nombre, devuelve la lista completa.
        }

        // Dividir el filtro en palabras clave para buscar coincidencias parciales
        String[] palabrasClave = filtroNombre.toLowerCase().split("\\s+");

        // Filtrar las recetas que contengan alguna de las palabras clave en su nombre
        return listaRecetas.stream()
                .filter(receta -> {
                    String nombreReceta = receta.getNombre().toLowerCase();
                    // Comprobar si el nombre de la receta contiene alguna de las palabras clave
                    for (String palabra : palabrasClave) {
                        if (nombreReceta.contains(palabra)) {
                            return true;
                        }
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

}