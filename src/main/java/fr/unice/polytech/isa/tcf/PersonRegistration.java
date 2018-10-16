package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingPersonException;

import javax.ejb.Local;

@Local
public interface PersonRegistration {
    public int register(String name, String address);
}
