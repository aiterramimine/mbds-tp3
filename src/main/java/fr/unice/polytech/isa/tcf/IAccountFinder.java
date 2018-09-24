package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.Account;

import javax.ejb.Local;
import java.util.Optional;

@Local
public interface IAccountFinder {

    Optional<Account> findById(int id);

}
