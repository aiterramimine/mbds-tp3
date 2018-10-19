package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.Person;
import fr.unice.polytech.isa.tcf.exceptions.PersonNotFoundException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PersonFinder {

    Person findById(Integer id) throws PersonNotFoundException;

    Person findByName(String name) throws PersonNotFoundException;
}
