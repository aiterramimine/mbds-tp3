package fr.unice.polytech.isa.tcf.entities;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Administrator extends Person implements Serializable {

    public Administrator() {
        super();
    }

    public Administrator(String name, String address) {
        super(name, address);
    }
}
