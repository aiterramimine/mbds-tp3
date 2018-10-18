package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.Advisor;
import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingClientException;

import javax.ejb.Local;

@Local
public interface ClientRegistration {

    void register(String name, String address, String creditCardNum, double initialTransfer) throws AlreadyExistingClientException;

    int register (String name, String address, Advisor advisor) throws AlreadyExistingClientException;
}
