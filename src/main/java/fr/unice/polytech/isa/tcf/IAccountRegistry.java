package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.Account;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IAccountRegistry {
    void register(double initialTransfer);
}
