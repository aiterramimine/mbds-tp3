package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.Address;
import fr.unice.polytech.isa.tcf.entities.Advisor;
import fr.unice.polytech.isa.tcf.entities.Client;
import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingClientException;

import javax.ejb.Local;

@Local
public interface ClientRegistration {

    int register (String name, String town, String address, int zipcode, Advisor advisor);
    Client registerWithReturn(String name, String town, String address, int zipcode, Advisor advisor);
}
