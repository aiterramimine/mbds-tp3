package fr.unice.polytech.isa.tcf.entities;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Administrator extends Person implements Serializable {

    public Administrator() {
        super();
    }

    public Administrator(String name, Address address) {
        super(name, address);
    }

    @Override
    public boolean isAClient() {
        return false;
    }

    @Override
    public boolean isAnAdvisor() {
        return false;
    }

    @Override
    public boolean isAnAdministrator() {
        return true;
    }
}
