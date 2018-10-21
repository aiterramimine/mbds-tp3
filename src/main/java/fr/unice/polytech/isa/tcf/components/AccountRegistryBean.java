package fr.unice.polytech.isa.tcf.components;

import fr.unice.polytech.isa.tcf.IAccountFinder;
import fr.unice.polytech.isa.tcf.IAccountRegistry;
import fr.unice.polytech.isa.tcf.entities.Account;
import fr.unice.polytech.isa.tcf.entities.Address;
import fr.unice.polytech.isa.tcf.entities.Client;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class AccountRegistryBean implements IAccountRegistry {

    private static final Logger log = Logger.getLogger(Logger.class.getName());

    @PersistenceContext
    private EntityManager em;

    @Override
    public int register(double initialTransfer, String clientName) {

        Account a = new Account();
        a.setBalance(initialTransfer);
        em.persist(a);
        em.flush();

        return a.getId();
    }

    @Override
    public int register(double initialTransfer, String clientName, String clientTown, String clientAddress, int clientZipcode) {

        Account a = new Account();
        a.setBalance(initialTransfer);

        Client c = new Client();
        c.setName(clientName);
        c.setAddress(new Address(clientTown, clientAddress, clientZipcode));

        a.setOwner(c);

        em.persist(a);
        em.flush();

        return a.getId();
    }

    @Override
    public int register(double initialTransfer, Client client) {
        Client c = em.merge(client);

        Account a = new Account();
        a.setBalance(initialTransfer);
        a.setOwner(c);

        c.addOwnAccount(a);

        em.persist(a);

        em.flush();

        return a.getId();
    }


}
