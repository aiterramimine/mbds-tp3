package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingClientException;

import javax.ejb.Local;

@Local
public interface ClientRegistration {

    void register(String name, String creditCardNum, double initialTransfer) throws AlreadyExistingClientException;

}
