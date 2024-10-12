package modelo;

import java.util.List;

public class Foro {
    private List<Resena> listaResena;
    private String name;

    public Foro(List<Resena> listaResena, String name) {
        this.listaResena = listaResena;
        this.name = name;
    }

    public List<Resena> getListaResena() {
        return listaResena;
    }

    public void setListaResena(List<Resena> listaResena) {
        this.listaResena = listaResena;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
