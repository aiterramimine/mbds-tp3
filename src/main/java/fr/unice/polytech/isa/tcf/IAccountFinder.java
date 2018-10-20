package fr.unice.polytech.isa.tcf;

import fr.unice.polytech.isa.tcf.entities.Account;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

@Local
public interface IAccountFinder {

    Optional<Account> findById(int id);

    List<Account> findAll();

    List<Account> findAllForUser(int id);

    List<Account> findAllComanagedForUser(int id);

    List<Account> findAllForAdvisor(int id);
}
