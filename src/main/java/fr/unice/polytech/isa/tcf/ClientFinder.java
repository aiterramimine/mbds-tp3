package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.Client;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface ClientFinder {

    Optional<Client> findByName(String name);

}
