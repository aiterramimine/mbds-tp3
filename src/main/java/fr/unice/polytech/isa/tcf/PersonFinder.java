package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.Person;

import javax.ejb.Local;
import java.util.List;

@Local
public interface PersonFinder {

    Person findById(Integer id);
}
