package fr.unice.polytech.isa.tcf.exceptions;

import java.io.Serializable;

public class PersonNotFoundException extends Exception implements Serializable {
    int id;
    String name;

    public PersonNotFoundException(String message) {
        super(message);
    }

}
