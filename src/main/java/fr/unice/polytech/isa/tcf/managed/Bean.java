package fr.unice.polytech.isa.tcf.managed;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named(value = "bean")
@RequestScoped
public class Bean {

    private int nombre;

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public List<Integer> getNombresSuivants() {
        int nb = 5;
        List<Integer> t = new ArrayList<>(nb);
        for (int i = nombre; i < nombre + nb; i++) {
            t.add(i);
        }
        return t;
    }
}