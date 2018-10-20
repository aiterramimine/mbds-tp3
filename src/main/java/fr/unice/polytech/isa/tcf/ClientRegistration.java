package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.Advisor;
import fr.unice.polytech.isa.tcf.entities.Client;
import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingClientException;

import javax.ejb.Local;

@Local
public interface ClientRegistration {

    int register (String name, String address, Advisor advisor);
    Client registerWithReturn(String name, String address, Advisor advisor);
}
