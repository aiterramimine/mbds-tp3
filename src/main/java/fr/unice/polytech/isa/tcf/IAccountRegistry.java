package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.Account;
import fr.unice.polytech.isa.tcf.entities.Client;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IAccountRegistry {
    int register(double initialTransfer, String cilentName);
    int register(double initialTransfer, String clientName, String clientAddress);
    int register(double initialTransfer, Client client);
}
